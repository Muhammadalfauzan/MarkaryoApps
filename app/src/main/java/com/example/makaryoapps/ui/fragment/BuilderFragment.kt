package com.example.makaryoapps.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentBuilderBinding
import com.example.makaryoapps.ui.category.DetailCategoryAdapter
import com.example.makaryoapps.ui.category.DetailCategoryModel

class BuilderFragment : Fragment(), DetailCategoryAdapter.OnItemClickListener {

    private var _binding: FragmentBuilderBinding? = null
    private val binding get() = _binding!!
    private val dataBuilder: ArrayList<DetailCategoryModel> = ArrayList()
    private lateinit var listDetailCategoryAdapter: DetailCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuilderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getDataBuilder()
        iconBackClicked()
    }

    @SuppressLint("Recycle")
    private fun getDataBuilder() {
        dataBuilder.clear() // Clear the list to avoid duplication
        val photo = resources.obtainTypedArray(R.array.data_photo)
        val names = resources.getStringArray(R.array.data_craftsman)
        val ratingsStringArray = resources.getStringArray(R.array.data_star)
        val ratingsFloatArray = ratingsStringArray.map { it.toFloat() }.toFloatArray()
        val skill1 = resources.getStringArray(R.array.data_skill1)
        val skillTextWithDoubleSpace = skill1.map { it.replace(" ", "  ") }.toTypedArray()
        val imgStatus = resources.obtainTypedArray(R.array.data_img_status)
        val status = resources.getStringArray(R.array.data_statusCrafts)

        for (i in names.indices) {
            val detailCategory = DetailCategoryModel(
                photo.getResourceId(i, -1),
                names[i],
                ratingsFloatArray[i],
                skillTextWithDoubleSpace[i],
                imgStatus.getResourceId(i % imgStatus.length(), -1),
                status[i % status.size]
            )
            dataBuilder.add(detailCategory)
        }

        photo.recycle()
        imgStatus.recycle()

        listDetailCategoryAdapter.submitList(dataBuilder) // Set the data to the adapter
    }

    private fun setupRecyclerView() {
        binding.rvBuilder.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        listDetailCategoryAdapter = DetailCategoryAdapter(this) // Initialize the adapter
        binding.rvBuilder.adapter = listDetailCategoryAdapter
    }

    private fun iconBackClicked() {
        binding.ibBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    override fun onItemClick(data :DetailCategoryModel) {
        Log.d("Item clicked", "Builder Item clicked")
        val bundle = bundleOf("item" to data)
        findNavController().navigate(R.id.action_builderFragment_to_detailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
