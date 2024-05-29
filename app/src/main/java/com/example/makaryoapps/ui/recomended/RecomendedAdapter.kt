package com.example.makaryoapps.ui.recomended

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemRekomendasiBinding

class RecomendedAdapter(private var recommendedList: MutableList<RecomendedModel>) :
    RecyclerView.Adapter<RecomendedAdapter.RecommendedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {
        val binding = ItemRekomendasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        val recommendedModel = recommendedList[position]
        holder.bind(recommendedModel)
    }

    override fun getItemCount(): Int = recommendedList.size

    fun replaceData(newList: List<RecomendedModel>) {
        recommendedList.clear()
        recommendedList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class RecommendedViewHolder(private val binding: ItemRekomendasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendedModel: RecomendedModel) {
            binding.imgRec.setImageResource(recommendedModel.imageRec)
            binding.tvName.text = recommendedModel.nameBuilder
            binding.tvRatting.setImageResource(recommendedModel.icRatting)
            binding.tvNilaiRatting.text = recommendedModel.nilaiRatting.toString()
            binding.tvSkill.text = recommendedModel.skill
        }
    }
}
