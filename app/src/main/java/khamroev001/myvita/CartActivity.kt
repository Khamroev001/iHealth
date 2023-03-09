package khamroev001.myvita

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.ContentInfoCompat.Flags
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import khamroev001.myvita.adapter.CartAdapter
import khamroev001.myvita.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var items:MutableList<khamroev001.myvita.model.Item>
    lateinit var items_filtered:MutableList<khamroev001.myvita.model.Item>


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        items = intent.getSerializableExtra("list") as MutableList<khamroev001.myvita.model.Item>

         items_filtered = items.filter { it.addToCart }.toMutableList()


        val adapter =
            CartAdapter(
                this,
                items_filtered as MutableList<khamroev001.myvita.model.Item>,
                object : CartAdapter.MyInterface {
                    override fun myfun(
                        subtotal: Int,
                        count: Int,
                    ) {
                        binding.subTotal.text = subtotal.toString()
                        binding.itemCount.text = "$count   supplements"
                        binding.delivery.text = (count * 2).toString()
                        binding.totalCost.text = (binding.subTotal.text.toString()
                            .toInt() + binding.delivery.text.toString().toInt()).toString()
                    }
                })

        val manager: LayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.rv.adapter = adapter
        binding.rv.layoutManager = manager
        var k = 0
        var s = 0


        for (i in items_filtered) {
            k += i.count
            s += i.count * i.price
        }
        binding.itemCount.text = k.toString() + "  ${binding.itemCount.text.toString()} "
        binding.subTotal.text = s.toString()
        binding.delivery.text = (k * 2).toString()
        binding.totalCost.text =
            (binding.subTotal.text.toString().toInt() + binding.delivery.text.toString()
                .toInt()).toString()

        binding.back.setOnClickListener {
           onBackPressed()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        for (i in 0 until items.size) {
            if (!items_filtered.contains(items[i])) {
                items[i].addToCart = false
            }
        }

        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("addedList", items as java.io.Serializable)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

}