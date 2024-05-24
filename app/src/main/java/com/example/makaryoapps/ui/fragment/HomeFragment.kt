package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentHomeBinding
import com.example.makaryoapps.ui.category.CategoryAdapter
import com.example.makaryoapps.ui.category.CategoryModel
import com.example.makaryoapps.ui.recomended.RecomendedAdapter
import com.example.makaryoapps.ui.recomended.RecomendedModel


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView pertama
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Data untuk RecyclerView pertama
        val dataFirst = listOf(
            CategoryModel(R.drawable.builder),
            CategoryModel(R.drawable.builder),
            CategoryModel(R.drawable.builder),
            CategoryModel(R.drawable.builder)
        )
        // Set adapter pertama ke RecyclerView pertama
        val firstAdapter = CategoryAdapter(dataFirst)
        binding.recyclerView.adapter = firstAdapter

        // Inisialisasi RecyclerView kedua
        binding.rvRekomendasi.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Data untuk RecyclerView kedua
        val dataSecond = listOf(
            RecomendedModel(R.drawable.rec_1, "Name Builder", "Service 1", R.drawable.ic_ratting, "9.0", "Skill 1"),
            RecomendedModel(R.drawable.rec_1, "Name Builder", "Service 2", R.drawable.ic_ratting, "9.5", "Skill 2"),
            RecomendedModel(R.drawable.rec_1, "Name Builder", "Service 3", R.drawable.ic_ratting, "8.5", "Skill 3"),
            RecomendedModel(R.drawable.rec_1, "Name Builder", "Service 4", R.drawable.ic_ratting, "8.0", "Skill 4")
        )

        // Set adapter kedua ke RecyclerView kedua
        val secondAdapter = RecomendedAdapter(dataSecond)
        binding.rvRekomendasi.adapter = secondAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

