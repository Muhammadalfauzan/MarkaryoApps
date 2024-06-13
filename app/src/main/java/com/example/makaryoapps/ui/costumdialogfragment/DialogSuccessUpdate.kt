package com.example.makaryoapps.ui.costumdialogfragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.makaryoapps.R

class DialogSuccessUpdate : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the custom layout for this dialog
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return inflater.inflate(R.layout.dialog_success_update_address, container, false)
    }

    override fun onStart() {
        super.onStart()

        // Set the dialog window animation
        dialog?.window?.setWindowAnimations(R.style.DialogAnimation)

        /*        // Set the dialog window attributes to be centered horizontally
                dialog?.window?.setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )*/

        val params = dialog?.window?.attributes
        params?.y = resources.displayMetrics.heightPixels / -2  // Move down to 25% of the screen height
        dialog?.window?.attributes = params


        // Dismiss the dialog after 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            dismiss()
        }, 2000) // 2000 ms = 2 seconds
    }
}
