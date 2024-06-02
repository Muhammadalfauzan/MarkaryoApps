package com.example.makaryoapps.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemReviewBinding

class ReviewAdapter  (private var itemList : List<ReviewModel>) : RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {

    class MyViewHolder ( val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.ivReview.setImageResource(item.imgReview)
        holder.binding.tvReviewName.text = item.nameReview
        holder.binding.tvKetReview.text = item.ketReview

    } override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateData(newReviews: List<ReviewModel>) {
        itemList = newReviews
        notifyDataSetChanged()
    }
}