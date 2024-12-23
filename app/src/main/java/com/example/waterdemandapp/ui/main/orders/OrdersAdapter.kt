package com.example.waterdemandapp.ui.main.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waterdemandapp.ui.Home
import com.example.waterdemandapp.databinding.ItemOrderBinding
import com.google.firebase.auth.FirebaseAuth

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    private var ordersList: List<Home> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val home = ordersList[position]
        holder.bind(home)
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    // Update the list of orders
    fun submitList(orders: List<Home>) {
        ordersList = orders
        notifyDataSetChanged() // Notify adapter that the data has changed
    }

    class OrdersViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(home: Home) {
            // Bind the data to the TextViews
            binding.userName.text = home.name
            binding.userAddress.text = home.address

            // Calculate the total price (liters * 20)
            val price = home.liters * 20
            binding.totalPrice.text = "$price PKR"

            // Set the default status to "Pending"
            binding.status.text = home.status

            // Check if the current user is the admin and set button visibility
            val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email
            if (currentUserEmail == "usmanaliadmin3833184@gmail.com") {
                // Show the buttons if the user is admin
                binding.placeOrderButton.visibility = View.VISIBLE
                binding.cancelButton.visibility = View.VISIBLE
            } else {
                // Hide the buttons if the user is not admin
                binding.placeOrderButton.visibility = View.GONE
                binding.cancelButton.visibility = View.GONE
            }
        }
    }
}
