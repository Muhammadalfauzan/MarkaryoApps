package com.example.makaryoapps.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentDetailChatBinding
import com.example.makaryoapps.ui.category.DetailCategoryModel
import com.example.makaryoapps.ui.chat.ChatModel


class DetailChatFragment : Fragment() {

    private var _binding: FragmentDetailChatBinding? = null
    private val binding get() = _binding!!

    private var previousSoftInputMode: Int? = null
    private var item: ChatModel? = null
    private var currentAnimator: Animator? = null
    private val shortAnimationDuration: Int by lazy {
        resources.getInteger(android.R.integer.config_shortAnimTime)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailChatBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
        iconBackClicked()


        binding.textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    binding.imageButton2.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.yellow
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
        private fun setData() {
            @Suppress("DEPRECATION")
            val item = arguments?.getParcelable<ChatModel>("item")

            item?.let {
                binding.profileImage.setImageResource(it.image)
                binding.textView17.text = it.name

                binding.profileImage.setOnClickListener { _ ->
                    zoomImageFromThumb(binding.profileImage, it.image)
                }
            }
    }

    private fun iconBackClicked() {
        binding.ibBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    private fun zoomImageFromThumb(thumbView: View, imageResId: Int) {
        currentAnimator?.cancel()

        val expandedImageView = binding.expandedImage
        expandedImageView.setImageResource(imageResId)

        val startBounds = Rect()
        val finalBounds = Rect()
        val globalOffset = Point()

        thumbView.getGlobalVisibleRect(startBounds)
        binding.root.getGlobalVisibleRect(finalBounds, globalOffset)
        startBounds.offset(-globalOffset.x, -globalOffset.y)
        finalBounds.offset(-globalOffset.x, -globalOffset.y)

        val startScale: Float
        if ((finalBounds.width() / finalBounds.height().toFloat()) >
            (startBounds.width() / startBounds.height().toFloat())
        ) {
            startScale = startBounds.height() / finalBounds.height().toFloat()
            val startWidth = startScale * finalBounds.width()
            val deltaWidth = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            startScale = startBounds.width() / finalBounds.width().toFloat()
            val startHeight = startScale * finalBounds.height()
            val deltaHeight = (startHeight - startBounds.height()) / 2
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        thumbView.alpha = 0f
        expandedImageView.visibility = View.VISIBLE

        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        val set = AnimatorSet()
        set
            .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left.toFloat(), finalBounds.left.toFloat()))
            .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top.toFloat(), finalBounds.top.toFloat()))
            .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
            .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
        set.duration = shortAnimationDuration.toLong()
        set.interpolator = DecelerateInterpolator()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                currentAnimator = null
            }

            override fun onAnimationCancel(animation: Animator) {
                currentAnimator = null
            }
        })
        set.start()
        currentAnimator = set

        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            val set = AnimatorSet()
            set.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left.toFloat()))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top.toFloat()))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
            set.duration = shortAnimationDuration.toLong()
            set.interpolator = DecelerateInterpolator()
            set.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    thumbView.alpha = 1f
                    expandedImageView.visibility = View.GONE
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    thumbView.alpha = 1f
                    expandedImageView.visibility = View.GONE
                    currentAnimator = null
                }
            })
            set.start()
            currentAnimator = set
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