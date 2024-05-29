package com.example.makaryoapps.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentBuilderBinding
import com.example.makaryoapps.databinding.FragmentOtomotifBinding
import com.example.makaryoapps.ui.category.DetailCategoryAdapter
import com.example.makaryoapps.ui.category.DetailCategoryModel


class OtomotifFragment : Fragment() {

    private var _binding: FragmentOtomotifBinding? = null
    private val binding get() = _binding!!

    private var dataBuilder: ArrayList<DetailCategoryModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtomotifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataBuilder() // Panggil fungsi untuk mendapatkan dataBuilder
        showRecycler()
        iconBackClicked()
    }

    @SuppressLint("Recycle")
    private fun getDataBuilder() {
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
    }

    private fun showRecycler() {
        binding.rvOtomotif.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        val listDetailCategoryAdapter = DetailCategoryAdapter(dataBuilder)
        binding.rvOtomotif.adapter = listDetailCategoryAdapter
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