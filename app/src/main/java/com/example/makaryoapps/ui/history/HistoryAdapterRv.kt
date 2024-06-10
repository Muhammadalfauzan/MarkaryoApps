package com.example.makaryoapps.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemHistoryBinding

class HistoryAdapterRv (private val itemList : List<HistoryModel>) : RecyclerView.Adapter<HistoryAdapterRv.MyViewHolder>() {

    class MyViewHolder ( val binding:ItemHistoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapterRv.MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.ivBuilderHistory.setImageResource(item.imageBuilder)
        holder.binding.tvNameBuilderHistory.text= item.nameBuilder
        holder.binding.tvStatusHistory.text = item.statusOrder
        holder.binding.tvDateHistory.text = item.date

        holder.binding.btnReceipt.setOnClickListener {

        }

    } override fun getItemCount(): Int {
        return itemList.size
        }
}