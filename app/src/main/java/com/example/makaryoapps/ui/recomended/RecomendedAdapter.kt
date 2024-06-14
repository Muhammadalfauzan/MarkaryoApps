package com.example.makaryoapps.ui.recomended

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemRekomendasiBinding

class RecomendedAdapter(private val listener: OnItemClickListener) :
    ListAdapter<RecomendedModel, RecomendedAdapter.RecomendedViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecomendedViewHolder {
        val binding = ItemRekomendasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecomendedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecomendedViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class RecomendedViewHolder(private val binding: ItemRekomendasiBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: RecomendedModel) {
            binding.apply {
                imgRec.setImageResource(item.imageRec)
                tvName.text = item.nameBuilder
                tvSkill.text = item.skill
                tvNilaiRatting.text = item.nilaiRatting.toString()
                tvLokasi.text = item.address
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(getItem(position))
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(data: RecomendedModel)
    }

    class DiffCallback : DiffUtil.ItemCallback<RecomendedModel>() {
        override fun areItemsTheSame(oldItem: RecomendedModel, newItem: RecomendedModel) =
            oldItem.nameBuilder == newItem.nameBuilder

        override fun areContentsTheSame(oldItem: RecomendedModel, newItem: RecomendedModel) =
            oldItem == newItem
    }
}
