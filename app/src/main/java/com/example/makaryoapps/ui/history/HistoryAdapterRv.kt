package com.example.makaryoapps.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemHistoryBinding

class HistoryAdapterRv(private val listener: OnItemClickListener) : RecyclerView.Adapter<HistoryAdapterRv.HistoryViewHolder>() {

    private val data = mutableListOf<HistoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<HistoryModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(history: HistoryModel) {
            binding.apply {
                ivBuilderHistory.setImageResource(history.imageBuilder)
                tvNameBuilderHistory.text = history.nameBuilder
                tvRatting.text = history.ratting
                tvStatusHistory.text = history.statusOrder
                tvDateHistory.text = history.date

                btnPesanLagi.setOnClickListener {
                    listener.onOrderAgainClick(history)
                }

                btnReceipt.setOnClickListener {
                    listener.onPrintReceiptClick(history)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onOrderAgainClick(data: HistoryModel)
        fun onPrintReceiptClick(data: HistoryModel)
    }
}
