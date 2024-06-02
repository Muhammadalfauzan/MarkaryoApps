package com.example.makaryoapps.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemDetailCraftsBinding

class DetailCategoryAdapter(
    private val itemList: ArrayList<DetailCategoryModel>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<DetailCategoryAdapter.MyViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(data: DetailCategoryModel)
    }

    // ViewHolder untuk item menggunakan View Binding
   inner class MyViewHolder(val binding: ItemDetailCraftsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: DetailCategoryModel) {
            binding.imageView3.setImageResource(category.imageCrafts)
            binding.tvCraftsName.text = category.nameCrafts
            binding.tvRatting.text = category.ratting.toString()
            binding.tvSkilled1.text = category.skill1

            binding.ivStatus.setImageResource(category.imgStatus)
            binding.tvStatusCraf.text = category.status

            itemView.setOnClickListener {
                listener.onItemClick(category)
            }
        }
    }

    // Inflate layout item dan buat ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemDetailCraftsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // Bind data ke ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
    override fun getItemCount(): Int = itemList.size
}
