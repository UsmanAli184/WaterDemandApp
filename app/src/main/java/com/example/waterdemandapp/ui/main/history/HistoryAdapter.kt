package com.example.waterdemandapp.ui.main.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waterdemandapp.ui.Home
import com.example.waterdemandapp.databinding.ItemHistoryBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var historyList: List<Home> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val home = historyList[position]
        holder.bind(home)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun submitList(history: List<Home>) {
        historyList = history
        notifyDataSetChanged()
    }

    class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(home: Home) {
            // Bind the name, date, and liters data
            binding.nameHistory.text = home.name

            // Get the current date
            val currentDate = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date())
            binding.date.text = "Placed on $currentDate"

            // Bind the liters value (from Home data)
            binding.liters.text = "${home.liters} Liters"
        }
    }
}
