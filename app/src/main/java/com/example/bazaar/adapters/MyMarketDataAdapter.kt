package com.example.bazaar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.R
import com.example.bazaar.model.Product
import com.example.bazaar.viewmodels.ListViewModel

class MyMarketDataAdapter(
    private var list: ArrayList<Product>,
    private val listener: OnItemClickListener,
    private val myMarketViewModel: ListViewModel
) :
    RecyclerView.Adapter<MyMarketDataAdapter.DataViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val productName: TextView = itemView.findViewById(R.id.myproductName)
        val price: TextView = itemView.findViewById(R.id.myproductPrice)
        val seller: TextView = itemView.findViewById(R.id.myproductSeller)

        private val button: Button = itemView.findViewById(R.id.button)

        init {
            itemView.setOnClickListener(this)
            button.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(currentPosition)
            if(this.button.isPressed) {
                list.removeAt(currentPosition)
                myMarketViewModel.removeProduct()
                notifyItemRemoved(currentPosition)
                notifyItemRangeChanged(currentPosition, list.size)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_item_layout, parent, false)
        return DataViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.productName.text = currentItem.title
        holder.price.text = currentItem.price_per_unit
        holder.seller.text = currentItem.username
    }

    override fun getItemCount() = list.size

    fun setData(newlist: ArrayList<Product>) {
        list = newlist
    }


}