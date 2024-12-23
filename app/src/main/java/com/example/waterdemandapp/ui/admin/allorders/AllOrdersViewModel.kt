package com.example.waterdemandapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.waterdemandapp.model.repositories.AllOrdersRepository
import com.example.waterdemandapp.ui.Home

class AllOrdersViewModel : ViewModel() {
    private val repository = AllOrdersRepository()

    private val _orders = MutableLiveData<List<Home>>()
    val orders: LiveData<List<Home>> get() = _orders

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchOrders() {
        repository.getOrders(
            onDataChange = { fetchedOrders -> _orders.postValue(fetchedOrders) },
            onFailure = { exception -> _error.postValue(exception.message) }
        )
    }

    fun updateOrderStatus(orderId: String, newStatus: String) {
        repository.updateOrderStatus(
            orderId,
            newStatus,
            onSuccess = { fetchOrders() }, // Refresh orders after status update
            onFailure = { exception -> _error.postValue(exception.message) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        repository.removeListener()
    }
}
