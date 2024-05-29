package com.example.makaryoapps.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemKategoriBinding

class CategoryAdapter( private val itemList: List<CategoryModel>,
    private val clickListener: OnCategoryClickListener,

) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    interface  OnCategoryClickListener {
        fun onCategoryClick(category :CategoryModel)
    }
    // ViewHolder untuk item menggunakan View Binding
    class MyViewHolder(val binding: ItemKategoriBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(category: CategoryModel){
            binding.ivCategory.setImageResource(category.imageCategory)
            binding.tvCategory.text= category.category
        }
    }

    // Inflate layout item dan buat ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // Bind data ke ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = itemList[position]
        holder.bind(category)
        holder.itemView.setOnClickListener {
            clickListener.onCategoryClick(category)
        }

    }

    override fun getItemCount(): Int = itemList.size

}
