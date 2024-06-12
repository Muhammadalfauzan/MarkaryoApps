package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentAddAddressBinding
import com.example.makaryoapps.ui.costumdialogfragment.CustomDialogFragment
import java.util.zip.Inflater


class AddAddressFragment : Fragment() {
    private var _binding: FragmentAddAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iconBackClicked()
        binding.btnSaveAddress.setOnClickListener {
            showCustomToast()
            findNavController().navigate(R.id.action_addAddressFragment_to_adressListFragment)
        }
    }
    private fun iconBackClicked() {
        binding.ibBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    private fun showCustomToast() {
        val dialog = CustomDialogFragment()
        dialog.show(parentFragmentManager, "CustomToastDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}