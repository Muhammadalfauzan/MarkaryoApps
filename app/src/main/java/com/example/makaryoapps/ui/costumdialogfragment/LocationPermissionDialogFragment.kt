package com.example.makaryoapps.ui.costumdialogfragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.example.makaryoapps.databinding.DialogPermisionLocationBinding



class LocationPermissionDialogFragment : DialogFragment() {

    private var _binding: DialogPermisionLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogPermisionLocationBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnIzinkan.setOnClickListener {
            requestLocationPermission()
            dismiss()
        }
    }

    private fun requestLocationPermission() {
        // Your logic to request location permission
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
