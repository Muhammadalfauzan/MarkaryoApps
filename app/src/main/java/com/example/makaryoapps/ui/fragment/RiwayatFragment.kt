package com.example.makaryoapps.ui.fragment

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
import com.example.makaryoapps.databinding.FragmentRiwayatBinding
import com.example.makaryoapps.ui.history.HistoryAdapterRv
import com.example.makaryoapps.ui.history.HistoryModel

class RiwayatFragment : Fragment(), HistoryAdapterRv.OnItemClickListener {

    private var _binding: FragmentRiwayatBinding? = null
    private val binding get() = _binding!!
    private lateinit var secondAdapter: HistoryAdapterRv

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHistoryRecyclerView()
    }

    private fun setupHistoryRecyclerView() {
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        secondAdapter = HistoryAdapterRv(this)
        binding.rvHistory.adapter = secondAdapter

        val dataHistory = listOf(
            HistoryModel(
                R.drawable.joko,
                "Jokoo",
                "5.0",
                "service sudah selesai",
                "15 Juni, 2024",
            ),
            HistoryModel(
                R.drawable.bambang,
                "Bambang",
                "4.5",
                "service sudah selesai",
                "16 Juni, 2024",
            ),

            HistoryModel(
                R.drawable.miekum,
                "Miekum",
                "4.7",
                "service sudah selesai",
                "17 Juni, 2024",
            ),
            HistoryModel(
                R.drawable.dendy_syahputra,
                "Dendy Syahputra",
                "4.3",
                "service sudah selesai",
                "18 Juni, 2024",
            ),
            HistoryModel(
                R.drawable.ari_blek,
                "Ari Blek",
                "5.0",
                "service sudah selesai",
                "16 Juni, 2024",
            )
            // Add more HistoryModel items as needed
        )
        secondAdapter.submitList(dataHistory)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOrderAgainClick(data: HistoryModel) {
        Log.d("RiwayatFragment", "Order Again clicked for: ${data.nameBuilder}, ${data.date}")
        val bundle = bundleOf("item" to data)
        findNavController().navigate(R.id.action_historyFragment_to_detailFragment, bundle)
    }

    override fun onPrintReceiptClick(data: HistoryModel) {
        Log.d("RiwayatFragment", "Print Receipt clicked for: ${data.nameBuilder}, ${data.date}")
        val bundle = bundleOf("item" to data)
        findNavController().navigate(R.id.action_historyFragment_to_receiptFragment, bundle)
    }
}
