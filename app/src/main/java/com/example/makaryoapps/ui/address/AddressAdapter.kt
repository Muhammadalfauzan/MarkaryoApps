package com.example.makaryoapps.ui.address

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.ItemAddressBinding
import com.example.makaryoapps.ui.chat.ChatAdapter
import com.example.makaryoapps.ui.chat.ChatModel


class AddressAdapter(
    private val listener: AddressClickListener
) : ListAdapter<AddressModel, AddressAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private var selectedPosition = 0

    interface AddressClickListener {
        fun onItemClick(address: AddressModel)
        fun onEditClick(address: AddressModel)
    }

    inner class MyViewHolder(val binding: ItemAddressBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(address: AddressModel, isSelected: Boolean) {
            binding.tvLabelAlamat.text = address.labelAlamat
            binding.tvName.text = address.name
            binding.tvAlamatLengkap.text = address.complateAddress

            binding.btnCheck.setImageResource(
                if (isSelected) R.drawable.ic_check_done else R.drawable.ic_check
            )

            binding.btnCheck.setOnClickListener {
                listener.onItemClick(address)
                selectedPosition = adapterPosition
                notifyDataSetChanged()
                showAddressSelectedNotification(binding.root.context, address.complateAddress)
            }

            binding.tvUpdateAddress.setOnClickListener {
                listener.onEditClick(address)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val isSelected = position == selectedPosition
        holder.bind(getItem(position), isSelected)
    }

    private fun showAddressSelectedNotification(context: Context, address: String) {
        Toast.makeText(context, "Alamat '$address' berhasil dipilih", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AddressModel>() {
            override fun areItemsTheSame(oldItem: AddressModel, newItem: AddressModel): Boolean {
                return oldItem.name == newItem.name && oldItem.labelAlamat == newItem.labelAlamat
            }

            override fun areContentsTheSame(oldItem: AddressModel, newItem: AddressModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
