package khamroev001.myvita.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import khamroev001.myvita.R

class CartAdapter(var context: Context, private val itemList: MutableList<khamroev001.myvita.model.Item>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var number: TextView = itemView.findViewById(R.id.number)
        var img: ImageView = itemView.findViewById(R.id.img)
        var delete: ImageView = itemView.findViewById(R.id.delete)
        var price: TextView = itemView.findViewById(R.id.price)
        var total_price: TextView = itemView.findViewById(R.id.total_sum)
        var minus: TextView = itemView.findViewById(R.id.minus)
        var add: TextView = itemView.findViewById(R.id.add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent,false)))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.name.text=item.name
        holder.price.text="$ "+item.price.toString()
        holder.img.setBackgroundResource(item.img)
        holder.number.text="1"


        holder.add.setOnClickListener {
            Toast.makeText(context,"one more ${item.name}  is added to cart ", Toast.LENGTH_SHORT).show()
            holder.number.text=(holder.number.text.toString().toInt()+1).toString()
        }
        holder.minus.setOnClickListener {
            if (holder.number.text.toString().toInt()>=2){
                Toast.makeText(context,"one ${item.name}  removed from cart ", Toast.LENGTH_SHORT).show()
                holder.number.text=(holder.number.text.toString().toInt()-1).toString()

            }
        }
        holder.delete.setOnClickListener{
            item.addToCart=false
           itemList.removeAt(position)
            notifyDataSetChanged()
        }
        holder.total_price.text="$ "+item.price.toString().toInt()*holder.number.text.toString().toInt()
    }


}

