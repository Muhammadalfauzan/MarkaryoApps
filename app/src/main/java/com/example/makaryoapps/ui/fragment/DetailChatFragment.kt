package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentDetailChatBinding


class DetailChatFragment : Fragment() {

    private var _binding: FragmentDetailChatBinding? = null
    private val binding get() = _binding!!

    private var previousSoftInputMode: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    binding.imageButton2.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.send_button_enabled
                        )
                    )
                } else {
                    binding.imageButton2.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.send_button_disabled
                        )
                    )
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })

        // Ensure the layout scrolls when EditText gains focus
        binding.textInputEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.scrollView.post {
                    binding.scrollView.scrollTo(0, binding.scrollView.bottom)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.let { window ->
            // Save the current soft input mode
            previousSoftInputMode = window.attributes.softInputMode
            // Set soft input mode to adjustResize
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.let { window ->
            // Restore the previous soft input mode
            previousSoftInputMode?.let {
                window.setSoftInputMode(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}