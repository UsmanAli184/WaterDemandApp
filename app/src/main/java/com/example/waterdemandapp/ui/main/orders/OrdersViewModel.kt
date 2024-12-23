package com.example.waterdemandapp.ui.main.orders
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.waterdemandapp.model.repositories.OrdersRepository
import com.example.waterdemandapp.ui.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val ordersRepository: OrdersRepository
) : ViewModel() {

    private val _orders = MutableLiveData<List<Home>>()
    val orders: LiveData<List<Home>> get() = _orders

    init {
        fetchOrders()
    }

    private fun fetchOrders() {
        ordersRepository.getOrders { orders ->
            _orders.value = orders
        }
    }

    // Cleanup listener when viewmodel is cleared
    override fun onCleared() {
        super.onCleared()
        ordersRepository.removeListener()
    }
} 