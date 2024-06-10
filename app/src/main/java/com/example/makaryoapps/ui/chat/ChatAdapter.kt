package com.example.makaryoapps.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.databinding.ItemChatBinding

class ChatAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<ChatModel, ChatAdapter.ChatViewHolder>(DIFF_CALLBACK) {

    inner class ChatViewHolder(private val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: ChatModel) {
            binding.profileImage.setImageResource(chat.image)
            binding.textView17.text = chat.name
            binding.textView19.text = chat.lastChat
            binding.tvContoh.text = chat.jam

            itemView.setOnClickListener {
                onItemClickListener.onItemClick(chat)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickListener {
        fun onItemClick(chat: ChatModel)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChatModel>() {
            override fun areItemsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
                return oldItem.name == newItem.name && oldItem.lastChat == newItem.lastChat
            }

            override fun areContentsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
