package com.example.makaryoapps.ui.fragment

import com.example.makaryoapps.ui.address.AddressAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentAdressListBinding

import com.example.makaryoapps.ui.address.AddressModel


class AdressListFragment : Fragment(), AddressAdapter.OnItemClickListener {

    private var _binding: FragmentAdressListBinding? = null
    private val binding get() = _binding!!
    private var dataAddress: ArrayList<AddressModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdressListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataAddress()
        showRecycler()
        iconBackClicked()


    }

    private fun getDataAddress() {

        val label =  resources.getStringArray(R.array.data_labelAlamat)
        val name =  resources.getStringArray(R.array.data_nameAlamat)
        val alamat =  resources.getStringArray(R.array.data_complateAlamat)

        for (i in label.indices) {
            val address = AddressModel(
                label[i],
                name[i],
                alamat[i]
            )
            dataAddress.add(address)
        }
    }

    private fun showRecycler() {
        binding.rvAddressList.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        val listDetailCategoryAdapter = AddressAdapter(dataAddress, this,)
        binding.rvAddressList.adapter = listDetailCategoryAdapter
    }
    override fun onItemClickChat(address: AddressModel) {

    }

    private fun iconBackClicked() {
        binding.ibBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}