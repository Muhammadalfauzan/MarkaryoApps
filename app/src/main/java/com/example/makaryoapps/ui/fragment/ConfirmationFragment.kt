package com.example.makaryoapps.ui.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentConfirmationBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ConfirmationFragment : Fragment()/*, MainActivity.PaymentMethodListener*/ {
    private var _binding: FragmentConfirmationBinding? = null
    private val binding get() = _binding!!
/*    private val viewModel: ConfirmationViewModel by viewModels()*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iconBackClicked()

        val craftsmanName = arguments?.getString("craftsman_name")
        val price = arguments?.getString("price")
        val photo = arguments?.getInt("photo_id")

        binding.tvNameCrafts.text = craftsmanName
        binding.textView12.text = price
        if (photo != null) {
            binding.imageView5.setImageResource(photo)
        }

        Log.d("ConfirmationFragment", "String Value: $craftsmanName, Int Value: $price")

        val currentDate = getCurrentDate()
        binding.tvDate.text = currentDate

        binding.tvAlamatUtama.setOnClickListener {
            findNavController().navigate(R.id.action_confirmationFragment_to_adressListFragment)
        }

        binding.btnOrderNow.setOnClickListener {
            dialogSukses()
        }
/*
        setupRadioGroup()*/

     /*   binding.tvLihatSemua.setOnClickListener {
            showPaymentMethodFragment()
        }

        // Observe the LiveData from the ViewModel
        viewModel.selectedPaymentMethod.observe(viewLifecycleOwner, Observer { paymentMethod ->
            binding.textView13.text = paymentMethod
        })
*/
        // Retrieve and use the selected payment method from arguments, if available
      /*  arguments?.getString("selectedPaymentMethod")?.let { paymentMethod ->
            viewModel.selectPaymentMethod(paymentMethod)
        }*/
    }

/*    private fun setupRadioGroup() {
        binding.radioGroupPayment.setOnCheckedChangeListener { group, checkedId ->
            val selectedPaymentMethod = when (checkedId) {
                R.id.radio_bca -> "BCA"
                R.id.radio_mandiri -> "Mandiri"
                R.id.radio_dana -> "Dana"
                else -> ""
            }

            if (selectedPaymentMethod.isNotEmpty()) {
                Log.d("PaymentMethod", "Selected Payment Method: $selectedPaymentMethod")

                // Update the LiveData in ViewModel
                viewModel.selectPaymentMethod(selectedPaymentMethod)

                // Update TextView directly
                binding.textView13.text = selectedPaymentMethod

                // Make tv_pilih_pembayaran GONE
                binding.tvPilihPembayaran.visibility = View.GONE

                Toast.makeText(requireContext(), "Option $selectedPaymentMethod is selected", Toast.LENGTH_SHORT).show()
            }
        }
    }*/
/*    private fun showPaymentMethodFragment() {
        val bottomSheet = PaymentMethodFragment()
        bottomSheet.setListener(this) // Set listener untuk menerima hasil pilihan metode pembayaran
        bottomSheet.show(childFragmentManager, PaymentMethodFragment::class.java.simpleName)
    }*/
    private fun dialogSukses() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_succes_order)

        val next = dialog.findViewById<Button>(R.id.btn_sukses)
        next.setOnClickListener {
            findNavController().navigate(R.id.action_confirmationFragment_to_historyFragment2)
            dialog.dismiss()
        }

        dialog.show()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

  /*  override fun onPaymentMethodSelected(paymentMethod: String, data: Bundle) {
        // Update the LiveData in ViewModel
        viewModel.selectPaymentMethod(paymentMethod)
    }*/
}
