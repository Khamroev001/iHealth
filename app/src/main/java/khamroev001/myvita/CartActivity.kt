package khamroev001.myvita

import android.content.ClipData.Item
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import khamroev001.myvita.adapter.CartAdapter
import khamroev001.myvita.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityCartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       var items=intent.getSerializableExtra("list") as MutableList<khamroev001.myvita.model.Item>

       var items_filtered=items.filter { it.addToCart }

        var adapter=CartAdapter(this,items_filtered as MutableList<khamroev001.myvita.model.Item>)

        var manager:LayoutManager=LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        binding.rv.adapter=adapter
        binding.rv.layoutManager=manager
    }
}