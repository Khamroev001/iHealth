package khamroev001.myvita

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import khamroev001.myvita.adapter.MainAdapter
import khamroev001.myvita.databinding.ActivityMainBinding
import khamroev001.myvita.model.Item
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var items:MutableList<Item>
    lateinit var adapter: MainAdapter
   var onfavpage by Delegates.notNull<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onfavpage=false
        items= mutableListOf()

        for (i in 0..50){
            if (i % 2==0){
                items.add(Item("Omega 3",R.drawable.omega3,20))
            }else{
                items.add(Item("Vitamin D3",R.drawable.vitamind3,10))
            }
        }
        val layoutManager = GridLayoutManager(this, 2)
        var adapter=MainAdapter(this,items)
        binding.rv.adapter=adapter

        binding.rv.layoutManager=layoutManager

        binding.favAppBarBtn.setOnClickListener {
            binding.search.clearFocus()
            binding.search.setText("")
            if (!onfavpage){
                var items_filtered=items.filter { it.isFavourite==true }
                var adapter_filtered=MainAdapter(this,items_filtered as MutableList<Item>)
                binding.rv.adapter=adapter_filtered
            }else{
                var adapter=MainAdapter(this,items)
                binding.rv.adapter=adapter
            }
            onfavpage =!onfavpage

        }
        binding.search.doOnTextChanged { text, start, before, count ->
            var items_filtered=items.filter { it.name.toUpperCase().contains(text.toString().toUpperCase()) }
            var adapter_filtered=MainAdapter(this,items_filtered as MutableList<Item>)
            binding.rv.adapter=adapter_filtered
        }
        binding.search.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.search.clearFocus()
            }
            if (event?.keyCode == KeyEvent.KEYCODE_BACK) { // Back button is pressed
            binding.search.clearFocus() // Remove the focus from EditText
                }
            false
        }

        binding.cartBtn.setOnClickListener{
            var intent=Intent(this,CartActivity::class.java)
            startActivity(intent)
        }





    }
}