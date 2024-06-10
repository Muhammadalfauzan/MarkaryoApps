package com.example.makaryoapps.ui.recomended

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemRekomendasiBinding

class RecomendedAdapter(private val listener: OnItemClickListener) :
    ListAdapter<RecomendedModel, RecomendedAdapter.RecommendedViewHolder>(DIFF_CALLBACK) {

    interface OnItemClickListener {
        fun onItemClick(data: RecomendedModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {
        val binding = ItemRekomendasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        val recommendedModel = getItem(position)
        holder.bind(recommendedModel)
    }

    inner class RecommendedViewHolder(private val binding: ItemRekomendasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendedModel: RecomendedModel) {
            binding.imgRec.setImageResource(recommendedModel.imageRec)
            binding.tvName.text = recommendedModel.nameBuilder
            binding.tvRatting.setImageResource(recommendedModel.icRatting)
            binding.tvNilaiRatting.text = recommendedModel.nilaiRatting.toString()
            binding.tvSkill.text = recommendedModel.skill

            itemView.setOnClickListener {
                listener.onItemClick(recommendedModel)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecomendedModel>() {
            override fun areItemsTheSame(oldItem: RecomendedModel, newItem: RecomendedModel): Boolean {
                return oldItem.imageRec == newItem.imageRec // Assuming there's a unique id for each item
            }

            override fun areContentsTheSame(oldItem: RecomendedModel, newItem: RecomendedModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
