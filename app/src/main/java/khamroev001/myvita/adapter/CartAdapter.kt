package khamroev001.myvita.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import khamroev001.myvita.R

class CartAdapter(
    var context: Context,
    private val itemList: MutableList<khamroev001.myvita.model.Item>,
    private var myInterface: MyInterface
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    interface MyInterface {
        fun myfun(subtotal: Int, count: Int)
    }

    private var total = 0
    private var count = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var number: TextView = itemView.findViewById(R.id.number)
        var img: ImageView = itemView.findViewById(R.id.img)
        var delete: ImageView = itemView.findViewById(R.id.delete)
        var price: TextView = itemView.findViewById(R.id.price)
        var totalPrice: TextView = itemView.findViewById(R.id.total_sum)
        var minus: TextView = itemView.findViewById(R.id.minus)
        var add: TextView = itemView.findViewById(R.id.add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        ))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.name.text = item.name
        holder.price.text = "$ " + item.price.toString()
        holder.img.setBackgroundResource(item.img)
        holder.number.text = item.count.toString()

        holder.add.setOnClickListener {
            var s = 0
            var k = 0
//            Toast.makeText(context,"one more ${item.name}  is added to cart ", Toast.LENGTH_SHORT).show()
            holder.number.text = (holder.number.text.toString().toInt() + 1).toString()
            item.count = holder.number.text.toString().toInt()
            for (i in itemList) {
                s += i.count * i.price
                k += i.count
            }
            total = s
            count = k
            myInterface.myfun(total, count)
            notifyItemChanged(position)
        }
        holder.minus.setOnClickListener {
            var s = 0
            var k = 0
            if (holder.number.text.toString().toInt() >= 2) {
//                Toast.makeText(context,"one ${item.name}  removed from cart ", Toast.LENGTH_SHORT).show()
                holder.number.text = (holder.number.text.toString().toInt() - 1).toString()
                item.count = holder.number.text.toString().toInt()
                for (i in itemList) {
                    s += i.count * i.price
                    k += i.count
                }
                total = s
                count = k
                myInterface.myfun(total, count)
                notifyItemChanged(position)
            }
        }

        holder.delete.setOnClickListener {
            var s = 0
            var k = 0
            item.addToCart = false
            itemList.removeAt(position)
            for (i in itemList) {
                s += i.count * i.price
                k += i.count
            }
            total = s
            count = k
            myInterface.myfun(total, count)
            notifyDataSetChanged()
        }
        holder.totalPrice.text = "$  " + item.price.toString().toInt() * item.count
    }


}

