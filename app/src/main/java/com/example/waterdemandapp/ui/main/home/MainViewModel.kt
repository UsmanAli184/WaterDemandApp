package com.example.waterdemandapp.ui.main.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waterdemandapp.model.repositories.MainRepository
import com.example.waterdemandapp.ui.Home
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    fun saveOrder(data: Home, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            repository.saveOrder(data, onSuccess, onFailure)
        }
    }
}
