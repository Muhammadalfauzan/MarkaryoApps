package com.example.makaryoapps.ui.recomended

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemKategoriBinding
import com.example.makaryoapps.databinding.ItemRekomendasiBinding
import com.example.makaryoapps.ui.category.CategoryModel

class RecomendedAdapter(private val itemList: List<RecomendedModel>) : RecyclerView.Adapter<RecomendedAdapter.MyViewHolder>() {

    // ViewHolder untuk item menggunakan View Binding
    class MyViewHolder(val binding: ItemRekomendasiBinding) : RecyclerView.ViewHolder(binding.root)

    // Inflate layout item dan buat ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRekomendasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // Bind data ke ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.imgRec.setImageResource(item.imageRec)
        holder.binding.tvName.text = item.nameBuilder
        holder.binding.tvService.text = item.service
        holder.binding.tvSkill.text = item.skill
        holder.binding.tvRatting.setImageResource(item.icRatting)
        holder.binding.tvNilaiRatting.text= item.nilaiRatting
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

}
