package com.example.makaryoapps.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemDetailCraftsBinding

class DetailCategoryAdapter(
    private val itemList: ArrayList<DetailCategoryModel>,
    /*private val itemClickListener: OnItemClickListener*/
) : RecyclerView.Adapter<DetailCategoryAdapter.MyViewHolder>() {
/*
    interface OnItemClickListener {
        fun onItemClick(category: DetailCategoryModel)
    }*/

    // ViewHolder untuk item menggunakan View Binding
    class MyViewHolder(val binding: ItemDetailCraftsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: DetailCategoryModel/*, clickListener: OnItemClickListener*/) {
            binding.imageView3.setImageResource(category.imageCrafts)
            binding.tvCraftsName.text = category.nameCrafts
            binding.tvRatting.text = category.ratting.toString()
            binding.tvSkilled1.text = category.skill1
       /*     binding.tvSkilled2.text = category.skill2
            binding.tvSkilled3.text = category.skill3*/
            binding.ivStatus.setImageResource(category.imgStatus)
            binding.tvStatusCraf.text = category.status
            // Set click listener on the itemView
          /*  itemView.setOnClickListener {
                clickListener.onItemClick(category)
            }*/
        }
    }

    // Inflate layout item dan buat ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemDetailCraftsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // Bind data ke ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = itemList[position]
        holder.bind(category)
     /*   holder.bind(category, itemClickListener)*/
    }

    override fun getItemCount(): Int = itemList.size
}
