package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentConfirmationBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ConfirmationFragment : Fragment() {
    private var _binding : FragmentConfirmationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentConfirmationBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iconBackClicked()

        val craftsmanName = arguments?.getString("craftsman_name")
        val price = arguments?.getInt("price")

        // Gunakan data sesuai kebutuhan, misalnya menampilkan nama tukang dan harga di TextView
        binding.tvNameCrafts.text = craftsmanName
        binding.textView12.text = price?.toString()

        Log.d("ConfirmationFragment", "String Value: $craftsmanName, Int Value: $price")

        val currentDate = getCurrentDate()
        binding.tvDate.text= currentDate

        binding.tvAlamatUtama.setOnClickListener {
            findNavController().navigate(R.id.action_confirmationFragment_to_adressListFragment)
        }

        binding.btnOrderNow.setOnClickListener {
            findNavController().navigate(R.id.action_confirmationFragment_to_historyFragment)
        }
    }
    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return sdf.format(Date())
    }
    private fun iconBackClicked() {
        binding.ibBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}