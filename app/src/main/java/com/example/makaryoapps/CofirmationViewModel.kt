package com.example.makaryoapps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfirmationViewModel : ViewModel() {
    private val _selectedPaymentMethod = MutableLiveData<String>()
    val selectedPaymentMethod: LiveData<String> get() = _selectedPaymentMethod

    fun selectPaymentMethod(method: String) {
        _selectedPaymentMethod.value = method
    }
}
