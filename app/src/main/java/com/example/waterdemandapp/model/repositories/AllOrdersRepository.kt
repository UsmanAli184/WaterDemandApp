package com.example.waterdemandapp.model.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.example.waterdemandapp.ui.Home
import com.google.firebase.firestore.ListenerRegistration

class AllOrdersRepository {

    private val db = FirebaseFirestore.getInstance()
    private val ordersCollection = db.collection("orders")
    private var listenerRegistration: ListenerRegistration? = null

    fun getOrders(onDataChange: (List<Home>) -> Unit, onFailure: (Exception) -> Unit) {
        listenerRegistration = ordersCollection.addSnapshotListener { snapshot, e ->
            if (e != null) {
                onFailure(e)
                return@addSnapshotListener
            }

            val orders = snapshot?.documents?.mapNotNull { it.toObject(Home::class.java)?.copy(id = it.id) } ?: emptyList()
            onDataChange(orders)
        }
    }

    fun updateOrderStatus(orderId: String, newStatus: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        ordersCollection.document(orderId).update("status", newStatus)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun removeListener() {
        listenerRegistration?.remove()
    }
}