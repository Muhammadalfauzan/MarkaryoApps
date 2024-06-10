package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentRiwayatBinding
import com.example.makaryoapps.ui.history.HistoryAdapterRv
import com.example.makaryoapps.ui.history.HistoryModel

class RiwayatFragment : Fragment() {

    private var _binding: FragmentRiwayatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataHistory = listOf(
            HistoryModel(
                R.drawable.rec_1,
                "Ibrahim",
                "4.7",
                "Service sudah selesai",
                "2 May, 2024"
            ),
            HistoryModel(
                R.drawable.rec_1,
                "Ibrahim",
                "4.7",
                "Service sudah selesai",
                "2 May, 2024"
            ),
            HistoryModel(
                R.drawable.rec_1,
                "Ibrahim",
                "4.7",
                "Service sudah selesai",
                "2 May, 2024"
            ),
            HistoryModel(
                R.drawable.rec_1,
                "Ibrahim",
                "4.7",
                "Service sudah selesai",
                "2 May, 2024"
            ),
            HistoryModel(
                R.drawable.rec_1,
                "Ibrahim",
                "4.7",
                "Service sudah selesai",
                "2 May, 2024"
            ),
        )

        val adapter = HistoryAdapterRv(dataHistory)
        binding.rvHistory.layoutManager = LinearLayoutManager(context)
        binding.rvHistory.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
