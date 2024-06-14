package com.example.makaryoapps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.FragmentDalamProsesBinding
import com.example.makaryoapps.databinding.FragmentRiwayatBinding
import com.example.makaryoapps.ui.history.HistoryAdapterRv
import com.example.makaryoapps.ui.history.HistoryModel
import com.example.makaryoapps.ui.proses.ProsesAdapter
import com.example.makaryoapps.ui.proses.ProsesModel


class DalamProsesFragment : Fragment() {


    private var _binding: FragmentDalamProsesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDalamProsesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataProses = listOf(
            ProsesModel(
                R.drawable.rec_1,
                "Ibrahim",
                "Sedang dikerjakan ",
                "2 Juni, 2024",
                "4.0",
                R.drawable.ic_dikerjakan
            ),
            ProsesModel(
                R.drawable.rec_1,
                "Ibrahim",
                "Sedang dikerjakan ",
                "17 Juni, 2024",
                "4.1",
                R.drawable.ic_dikerjakan
            ),
            ProsesModel(
                R.drawable.rec_1,
                "Ibrahim",
                "sedang dalam perjalanani",
                "14 Juni, 2024",
                "4.5",
                R.drawable.ic_proses
            ),
            ProsesModel(
                R.drawable.rec_1,
                "Ibrahim",
                "sedang dalam perjalanan",
                "15 Juni, 2024",
                "4.8",
                R.drawable.ic_proses
            ),

        )

        val adapter = ProsesAdapter(dataProses)
        binding.rvProses.layoutManager = LinearLayoutManager(context)
        binding.rvProses.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}