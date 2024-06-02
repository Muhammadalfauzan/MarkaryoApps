package com.example.makaryoapps.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemPortofolioBinding

class PortofolioAdapter (private val itemList : List<PortofolioModel>) : RecyclerView.Adapter<PortofolioAdapter.MyViewHolder>() {

    class MyViewHolder ( val binding: ItemPortofolioBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemPortofolioBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.ivPortofolio.setImageResource(item.imgPortofolio)

    } override fun getItemCount(): Int {
        return itemList.size
    }
}