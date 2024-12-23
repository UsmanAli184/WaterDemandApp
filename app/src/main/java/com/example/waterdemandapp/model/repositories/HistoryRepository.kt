package com.example.waterdemandapp.model.repositories

import com.example.waterdemandapp.ui.Home
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HistoryRepository @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()

    // Function to get order history data
    suspend fun getHistory(): List<Home> {
        return try {
            val ordersSnapshot = db.collection("orders").get().await()
            ordersSnapshot.toObjects(Home::class.java) // Fetching Home objects from Firestore
        } catch (e: Exception) {
            emptyList()
        }
    }
}
