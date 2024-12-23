package com.example.waterdemandapp.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waterdemandapp.model.repositories.HistoryRepository
import com.example.waterdemandapp.ui.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch // Import the coroutine scope launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _history = MutableLiveData<List<Home>>()
    val history: LiveData<List<Home>> get() = _history

    init {
        fetchHistory()
    }

    // Use viewModelScope to launch the coroutine
    private fun fetchHistory() {
        viewModelScope.launch {
            // Fetch history inside the coroutine
            val orders = historyRepository.getHistory()
            _history.value = orders
        }
    }
}
