package com.example.makaryoapps.ui.fragment

import com.example.makaryoapps.ui.address.AddressAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentAdressListBinding

import com.example.makaryoapps.ui.address.AddressModel


class AdressListFragment : Fragment(), AddressAdapter.AddressClickListener {

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
        showRecycler()
        iconBackClicked()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_adressListFragment_to_addAddressFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        getDataAddress()
    }

    private fun getDataAddress() {
        val label = resources.getStringArray(R.array.data_labelAlamat)
        val name = resources.getStringArray(R.array.data_nameAlamat)
        val alamat = resources.getStringArray(R.array.data_complateAlamat)
        val noHp = resources.getStringArray(R.array.data_noHpAlamat)

        dataAddress.clear() // Clear the existing data before adding new
        for (i in label.indices) {
            val address = AddressModel(
                label[i],
                name[i],
                alamat[i],
                noHp[i]
            )
            dataAddress.add(address)
        }
    }

    private fun showRecycler() {
        binding.rvAddressList.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        val addressAdapter = AddressAdapter(this)
        binding.rvAddressList.adapter = addressAdapter
        addressAdapter.submitList(dataAddress)
    }

    override fun onItemClick(address: AddressModel) {
        // Handle the item click here
    }

    override fun onEditClick(address: AddressModel) {
        val bundle = Bundle().apply {
            putString("labelAlamat", address.labelAlamat)
            putString("name", address.name)
            putString("alamatLengkap", address.complateAddress)
            putString("nomorHp", address.noHp)
        }
        findNavController().navigate(R.id.action_adressListFragment_to_updateAddressFragment, bundle)
    }

    private fun iconBackClicked() {
        binding.ibBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
