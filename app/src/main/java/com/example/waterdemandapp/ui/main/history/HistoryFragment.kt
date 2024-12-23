package com.example.waterdemandapp.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterdemandapp.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val historyViewModel: HistoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        val historyAdapter = HistoryAdapter()
        binding.historyRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.historyRecyclerview.adapter = historyAdapter

        // Observe the LiveData to get updates whenever data changes
        historyViewModel.history.observe(viewLifecycleOwner) { history ->
            historyAdapter.submitList(history)
        }

        return binding.root
    }
}
