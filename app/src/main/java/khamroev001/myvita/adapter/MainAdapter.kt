package khamroev001.myvita.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import khamroev001.myvita.R


class MainAdapter(var context: Context,private val itemList: MutableList<khamroev001.myvita.model.Item>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(khamroev001.myvita.R.id.name)
        var img: ImageView = itemView.findViewById(khamroev001.myvita.R.id.img)
        var price: TextView = itemView.findViewById(khamroev001.myvita.R.id.price)
        var add: ImageView = itemView.findViewById(khamroev001.myvita.R.id.add)
        var favourite: ImageView = itemView.findViewById(khamroev001.myvita.R.id.favourite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent,false)))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.name.text=item.name
        holder.price.text="$ "+item.price.toString()
        holder.img.setBackgroundResource(item.img)


        if (!item.isFavourite) {
            holder.favourite.setBackgroundResource(R.drawable.favorite_border)
        } else {
            holder.favourite.setBackgroundResource(R.drawable.favorite)
        }


        holder.favourite.setOnClickListener{
            if (item.isFavourite) {
                holder.favourite.setBackgroundResource(R.drawable.favorite_border)
            } else {
                holder.favourite.setBackgroundResource(R.drawable.favorite)
            }
            item.isFavourite = !item.isFavourite
        }

        holder.add.setOnClickListener {
            Toast.makeText(context,"${item.name}  is added to cart ",Toast.LENGTH_SHORT).show()
            item.addToCart=true
        }

    }
}
