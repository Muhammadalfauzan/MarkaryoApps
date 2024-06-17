package com.example.makaryoapps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.makaryoapps.databinding.FragmentPaymentMethodBinding
import com.example.makaryoapps.ui.activity.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PaymentMethodFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPaymentMethodBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: MainActivity.PaymentMethodListener

    // Method untuk mengatur listener
    fun setListener(listener: MainActivity.PaymentMethodListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroupPayment.setOnCheckedChangeListener { group, checkedId ->
            val selectedPaymentMethod = when (checkedId) {
                binding.radioVaBca.id -> "VA Bank BCA"
                binding.radioVaBtn.id -> "VA Bank BTN"
                // Tambahkan kondisi lain untuk radio button lainnya
                else -> ""
            }

            if (selectedPaymentMethod.isNotEmpty()) {
                val bundle = Bundle().apply {
                    putString("paymentMethod", selectedPaymentMethod)
                }
                listener.onPaymentMethodSelected(selectedPaymentMethod, bundle)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}