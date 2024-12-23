package com.example.waterdemandapp.model.repositories

import com.example.waterdemandapp.ui.Home
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ListenerRegistration
import javax.inject.Inject

class OrdersRepository @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()

    // Live listener registration for real-time updates
    private var listenerRegistration: ListenerRegistration? = null

    // Function to fetch initial orders and also listen for real-time updates
    fun getOrders(onOrdersUpdated: (List<Home>) -> Unit) {
        // Listen for real-time changes in the orders collection
        listenerRegistration = db.collection("orders")
            .addSnapshotListener { snapshot: QuerySnapshot?, e: Exception? ->
                if (e != null) {
                    // Handle error
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    // Convert the snapshot to a list of Home objects
                    val orders = snapshot.toObjects(Home::class.java)
                    onOrdersUpdated(orders)
                }
            }
    }

    // Clean up the listener when it's no longer needed
    fun removeListener() {
        listenerRegistration?.remove()
    }
}
