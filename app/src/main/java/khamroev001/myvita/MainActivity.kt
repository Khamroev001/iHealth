package khamroev001.myvita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import khamroev001.myvita.adapter.MainAdapter
import khamroev001.myvita.databinding.ActivityMainBinding
import khamroev001.myvita.model.Item

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var items:MutableList<Item>
    lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        items= mutableListOf()

        for (i in 0..50){
            if (i % 2==0){
                items.add(Item("Omega 3",R.drawable.omega3,20))
            }else{
                items.add(Item("Vitamin D3",R.drawable.vitamind3,10))
            }
        }
        val layoutManager = GridLayoutManager(this, 2)
        var adapter=MainAdapter(items)
        binding.rv.adapter=adapter

        binding.rv.layoutManager=layoutManager


    }
}