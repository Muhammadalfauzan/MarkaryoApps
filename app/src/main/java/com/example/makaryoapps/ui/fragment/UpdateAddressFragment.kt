package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentUpdateAddressBinding
import com.example.makaryoapps.ui.costumdialogfragment.DialogSuccessUpdate


@Suppress("DEPRECATION")
class UpdateAddressFragment : Fragment() {
    private var _binding: FragmentUpdateAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iconBackClicked()
        arguments?.let {
            binding.labelLay.setText(it.getString("labelAlamat"))
            binding.nameLay.setText(it.getString("name"))
            binding.addressLay.setText(it.getString("alamatLengkap"))
            binding.nomorHpLay.setText(it.getString("nomorHp"))
        }

        binding.btnSaveAddress.setOnClickListener {
            showCustomToast()
            findNavController().navigate(R.id.action_updateAddressFragment_to_adressListFragment)
        }
    }
    private fun iconBackClicked() {
        binding.ibBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    private fun showCustomToast() {
        val dialog = DialogSuccessUpdate()
        dialog.show(parentFragmentManager, "CustomToastDialog")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}