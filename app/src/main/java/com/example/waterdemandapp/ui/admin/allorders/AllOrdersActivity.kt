package com.example.waterdemandapp.ui.admin.allorders

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterdemandapp.databinding.ActivityAllOrdersBinding
import com.example.waterdemandapp.ui.AllOrdersAdapter
import com.example.waterdemandapp.viewmodel.AllOrdersViewModel

class AllOrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllOrdersBinding
    private val viewModel: AllOrdersViewModel by viewModels()
    private lateinit var adapter: AllOrdersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        viewModel.fetchOrders()
    }

    private fun setupRecyclerView() {
        adapter = AllOrdersAdapter(
            onPlaceOrder = { orderId -> viewModel.updateOrderStatus(orderId, "Placed") },
            onCancelOrder = { orderId -> viewModel.updateOrderStatus(orderId, "Cancelled") }
        )
        binding.allOrdersRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.allOrdersRecyclerview.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.orders.observe(this) { orders ->
            adapter.submitList(orders)
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    }
}
