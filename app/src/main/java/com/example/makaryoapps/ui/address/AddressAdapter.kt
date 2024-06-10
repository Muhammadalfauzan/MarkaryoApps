package com.example.makaryoapps.ui.address

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.ItemAddressBinding


class AddressAdapter(
    private val itemList: ArrayList<AddressModel>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<AddressAdapter.MyViewHolder>() {

    private var selectedPosition = 0

    interface OnItemClickListener {
        fun onItemClickChat(address: AddressModel)
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
                clickListener.onItemClickChat(address)
                selectedPosition = adapterPosition
                notifyDataSetChanged()
                showAddressSelectedNotification(binding.root.context, address.complateAddress)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position], position == selectedPosition)
    }

    override fun getItemCount(): Int = itemList.size

    private fun showAddressSelectedNotification(context: Context, address: String) {
        Toast.makeText(context, "Alamat '$address' berhasil dipilih", Toast.LENGTH_SHORT).show()
    }
}
