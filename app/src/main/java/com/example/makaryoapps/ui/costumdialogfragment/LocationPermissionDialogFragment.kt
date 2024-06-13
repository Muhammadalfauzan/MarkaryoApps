package com.example.makaryoapps.ui.costumdialogfragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.makaryoapps.databinding.DialogPermisionLocationBinding
import com.example.makaryoapps.ui.fragment.HomeFragment

class LocationPermissionDialogFragment : DialogFragment() {

    private var _binding: DialogPermisionLocationBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

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

        sharedPreferences = requireContext().getSharedPreferences(HomeFragment.PREFS_NAME, Context.MODE_PRIVATE)

        binding.btnIzinkan.setOnClickListener {
            setLocationPermission(true)
        }

        binding.btnLewatkan.setOnClickListener {
            setLocationPermission(false)
        }
    }

    private fun setLocationPermission(isGranted: Boolean) {
        val intent = Intent().apply {
            putExtra("isGranted", isGranted)
        }
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)

        // Save location permission choice to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putBoolean(HomeFragment.KEY_LOCATION_PERMISSION_GRANTED, isGranted)
        editor.putBoolean(HomeFragment.KEY_LOCATION_PERMISSION_DIALOG_SHOWN, true)
        editor.apply()

        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
