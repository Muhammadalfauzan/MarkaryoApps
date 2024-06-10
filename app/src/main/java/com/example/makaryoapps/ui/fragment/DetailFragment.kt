package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentDetailBinding
import com.example.makaryoapps.ui.category.DetailCategoryModel
import com.example.makaryoapps.ui.detail.PortofolioAdapter
import com.example.makaryoapps.ui.detail.PortofolioModel
import com.example.makaryoapps.ui.detail.ReviewAdapter
import com.example.makaryoapps.ui.detail.ReviewModel
import com.example.makaryoapps.ui.recomended.RecomendedModel


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var portofolioAdapter: PortofolioAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private var allReviews: List<ReviewModel> = listOf()
    private var limitedReviews: List<ReviewModel> = listOf()
    private var showingAllReviews: Boolean = false
    private var detailCategoryItem: DetailCategoryModel? = null
    private var recommendedItem: RecomendedModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        binding.btnOrderNow.setOnClickListener {
            val bundle = Bundle()
            recommendedItem?.let {
                bundle.putString("craftsman_name", it.nameBuilder)
                bundle.putInt("price", 19500)
                findNavController().navigate(R.id.action_detailFragment_to_confirmationFragment, bundle)
            } ?: detailCategoryItem?.let {
                bundle.putString("craftsman_name", it.nameCrafts)
                bundle.putInt("price", 19500)
                findNavController().navigate(R.id.action_detailFragment_to_confirmationFragment, bundle)
            }
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
            ReviewModel(R.drawable.jihan, "Jihan Audy", "Cukup memuaskan, pelayananuya baik\n" + "abangnya ramah")
        )

        limitedReviews = allReviews.take(2)
        reviewAdapter = ReviewAdapter(limitedReviews)
        binding.rvReview.adapter = reviewAdapter
    }

    private fun toggleReviews() {
        if (showingAllReviews) {
            reviewAdapter.updateData(limitedReviews)
            binding.tvLihatSemua.text = "Lihat Semua"
        } else {
            reviewAdapter.updateData(allReviews)
            binding.tvLihatSemua.text = "Tutup"
        }
        showingAllReviews = !showingAllReviews
    }

    private fun setData() {
        val data = arguments?.getParcelable<Parcelable>("item")
        when (data) {
            is RecomendedModel -> {
                recommendedItem = data
                binding.imgCraftsman.setImageResource(data.imageRec)
                binding.tvCraftsName.text = data.nameBuilder
                binding.tvSkilled.text = data.skill
                binding.tvRatting.text = data.nilaiRatting.toString()
            }
            is DetailCategoryModel -> {
                detailCategoryItem = data
                binding.imgCraftsman.setImageResource(data.imageCrafts)
                binding.tvCraftsName.text = data.nameCrafts
                binding.tvSkilled.text = data.skill1
                binding.tvRatting.text = data.ratting.toString()
            }
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


