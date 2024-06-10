package com.example.makaryoapps.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemDetailCraftsBinding


class DetailCategoryAdapter(private val listener: OnItemClickListener) : ListAdapter<DetailCategoryModel, DetailCategoryAdapter.MyViewHolder>(DIFF_CALLBACK) {

    interface OnItemClickListener {
        fun onItemClick(data: DetailCategoryModel)
    }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemDetailCraftsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailCategoryModel>() {
            override fun areItemsTheSame(oldItem: DetailCategoryModel, newItem: DetailCategoryModel): Boolean {
                return oldItem.imageCrafts == newItem.imageCrafts // Assuming there's a unique id for each item
            }

            override fun areContentsTheSame(oldItem: DetailCategoryModel, newItem: DetailCategoryModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
