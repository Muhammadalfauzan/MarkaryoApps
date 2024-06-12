package com.example.makaryoapps.ui.proses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemHistoryBinding
import com.example.makaryoapps.databinding.ItemProsesPesananBinding
import com.example.makaryoapps.ui.history.HistoryModel
import com.example.makaryoapps.ui.recomended.RecomendedModel

class ProsesAdapter (private val itemList : List<ProsesModel>) : RecyclerView.Adapter<ProsesAdapter.MyViewHolder>() {

    class MyViewHolder ( val binding: ItemProsesPesananBinding): RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(data: RecomendedModel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProsesAdapter.MyViewHolder {
        val binding = ItemProsesPesananBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.imageView3.setImageResource(item.imageCrafts)
        holder.binding.textView2.text= item.name
        holder.binding.tvNilaiRatting.text = item.ratting
        holder.binding.tvStatus.text = item.proses
        holder.binding.tvDate.text = item.date


    } override fun getItemCount(): Int {
        return itemList.size
    }
}