package com.example.makaryoapps.ui.fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentProfileBinding
import com.example.makaryoapps.ui.activity.ActivityLogin

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exitButton.setOnClickListener {
            dialogLogout()
        }

        binding.profilSaya.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_updateProfileFragment)
        }

        binding.location.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_adressListFragment)
        }

        binding.tvPusatBantuan.setOnClickListener {
            val url = "https://makaryo-web.vercel.app/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.beriRating.setOnClickListener {
            val url = "https://play.google.com/store/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.tvKebijakanPrivasi.setOnClickListener {
            val url = "https://makaryo-web.vercel.app/#faq"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun dialogLogout() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_exit)

        val btnExit = dialog.findViewById<Button>(R.id.btn_exit)
        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel)

        btnExit.setOnClickListener {
            logout()
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun logout() {
        sharedPreferences.edit().clear().apply()

        val locationPrefs = requireContext().getSharedPreferences(HomeFragment.PREFS_NAME, Context.MODE_PRIVATE)
        locationPrefs.edit().clear().apply()

        val intent = Intent(requireContext(), ActivityLogin::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
