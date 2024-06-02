package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentDetailBinding
import com.example.makaryoapps.databinding.FragmentHomeBinding
import com.example.makaryoapps.ui.category.CategoryAdapter
import com.example.makaryoapps.ui.category.CategoryModel
import com.example.makaryoapps.ui.category.DetailCategoryModel
import com.example.makaryoapps.ui.detail.PortofolioAdapter
import com.example.makaryoapps.ui.detail.PortofolioModel
import com.example.makaryoapps.ui.detail.ReviewAdapter
import com.example.makaryoapps.ui.detail.ReviewModel
import com.example.makaryoapps.ui.recomended.RecomendedAdapter


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var portofolioAdapter: PortofolioAdapter
    private lateinit var reviewAdapter: ReviewAdapter

    private var allReviews: List<ReviewModel> = listOf()
    private var limitedReviews: List<ReviewModel> = listOf()
    private var showingAllReviews: Boolean = false
    private var item: DetailCategoryModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        portofolioRecyclerView()
        reviewRecyclerView()
        setData()
        iconBackClicked()

        binding.tvLihatSemua.setOnClickListener {
            toggleReviews()
        }
    }

    private fun portofolioRecyclerView() {
        binding.rvPortofolio.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val dataFirst = listOf(
            PortofolioModel(R.drawable.builder),
            PortofolioModel(R.drawable.electronic),
            PortofolioModel(R.drawable.cleaning),
            PortofolioModel(R.drawable.otomotif)
        )
        // Initialize the adapter with the data
        portofolioAdapter = PortofolioAdapter(dataFirst)

        binding.rvPortofolio.adapter = portofolioAdapter
    }

    private fun reviewRecyclerView() {
        binding.rvReview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        allReviews = listOf(
            ReviewModel(R.drawable.jihan, "Jihan Audy", "Cukup memuaskan, pelayananuya baik\n" + "abangnya ramah"),
            ReviewModel(R.drawable.jihan, "Jihan Audy", "Cukup memuaskan, pelayananuya baik\n" + "abangnya ramah"),
            ReviewModel(R.drawable.jihan, "Jihan Audy", "Cukup memuaskan, pelayananuya baik\n" + "abangnya ramah"),
            ReviewModel(R.drawable.jihan, "Jihan Audy", "Cukup memuaskan, pelayananuya baik\n" + "abangnya ramah"),
            ReviewModel(R.drawable.jihan, "Jihan Audy", "Cukup memuaskan, pelayananuya baik\n" + "abangnya ramah"),
            ReviewModel(R.drawable.jihan, "Jihan Audy", "Cukup memuaskan, pelayananuya baik\n" + "abangnya ramah"),
        )

        // Show only the first 3 items initially
        limitedReviews = allReviews.take(2)

        // Initialize the adapter with the limited data
        reviewAdapter = ReviewAdapter(limitedReviews)
        binding.rvReview.adapter = reviewAdapter
    }

    private fun toggleReviews() {
        if (showingAllReviews) {
            // Show limited reviews
            reviewAdapter.updateData(limitedReviews)
            binding.tvLihatSemua.text = "Lihat Semua"
        } else {
            // Show all reviews
            reviewAdapter.updateData(allReviews)
            binding.tvLihatSemua.text = "Tutup"
        }
        showingAllReviews = !showingAllReviews
    }

    private fun setData() {
        @Suppress("DEPRECATION")
        item = arguments?.getParcelable("item")

        item?.let {
            binding.imgCraftsman.setImageResource(item!!.imageCrafts)
            binding.tvCraftsName.text = item?.nameCrafts
            binding.tvSkilled.text = item?.skill1
            binding.tvRatting.text = item?.ratting.toString()
        }
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
}

