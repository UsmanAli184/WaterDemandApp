package com.example.waterdemandapp.ui.main.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterdemandapp.databinding.FragmentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private val ordersViewModel: OrdersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        val ordersAdapter = OrdersAdapter()
        binding.ordersRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.ordersRecyclerview.adapter = ordersAdapter

        ordersViewModel.orders.observe(viewLifecycleOwner) { orders ->
            ordersAdapter.submitList(orders)
        }

        return binding.root
    }
}
