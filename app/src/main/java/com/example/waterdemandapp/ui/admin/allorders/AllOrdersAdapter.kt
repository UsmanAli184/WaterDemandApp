package com.example.waterdemandapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waterdemandapp.databinding.ItemOrderBinding

class AllOrdersAdapter(
    private val onPlaceOrder: (String) -> Unit,
    private val onCancelOrder: (String) -> Unit
) : ListAdapter<Home, AllOrdersAdapter.OrderViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
    }

    inner class OrderViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Home) {
            binding.userName.text = order.name
            binding.totalPrice.text = "${order.liters * 20} PKR" // Example price calculation
            binding.userAddress.text = "${order.houseNumber}, ${order.address}"
            binding.status.text = order.status

            binding.placeOrderButton.setOnClickListener { onPlaceOrder(order.id) }
            binding.cancelButton.setOnClickListener { onCancelOrder(order.id) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Home>() {
        override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
            return oldItem == newItem
        }
    }
}
