package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.makaryoapps.R

import com.example.makaryoapps.databinding.FragmentHistoryBinding
import com.example.makaryoapps.ui.history.HistoryPagerAdapter
import com.google.android.material.tabs.TabLayout


class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager(binding.tabLayout)
    }

    private fun setupViewPager(tabLayout: TabLayout) {
        val adapter = HistoryPagerAdapter(childFragmentManager)
        adapter.addFragment(DalamProsesFragment(), getString(R.string.dalam_proses))
        adapter.addFragment(RiwayatFragment(), getString(R.string.riwayat))
        binding.viewPager.adapter = adapter
        tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
