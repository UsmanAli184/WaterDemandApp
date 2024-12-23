package com.example.waterdemandapp.model.repositories
import com.example.waterdemandapp.ui.Home
import com.google.firebase.firestore.FirebaseFirestore

class MainRepository {
    private val firestore = FirebaseFirestore.getInstance()

    fun saveOrder(data: Home, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection("orders")
            .add(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
}
