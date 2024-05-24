package com.example.makaryoapps.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemKategoriBinding

class CategoryAdapter(private val itemList: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    // ViewHolder untuk item menggunakan View Binding
    class MyViewHolder(val binding: ItemKategoriBinding) : RecyclerView.ViewHolder(binding.root)

    // Inflate layout item dan buat ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // Bind data ke ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.ivCategory.setImageResource(item.imageCategory)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

}
