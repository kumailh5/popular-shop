package com.kumail.popularshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumail.popularshop.data.model.SaleItem
import com.kumail.popularshop.data.repository.MainRepository
import com.kumail.popularshop.network.ApiResponse
import com.kumail.popularshop.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by kumailhussain on 15/10/2021.
 */
@HiltViewModel
class MainViewModel @Inject internal constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: SingleLiveEvent<String> = _errorMessage

    private val _saleList = MutableLiveData<List<SaleItem>>()
    val saleList: LiveData<List<SaleItem>> = _saleList

    init {
        getSaleList()
    }

    private fun getSaleList() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            when (val result = mainRepository.getSaleList()) {
                is ApiResponse.Success -> _saleList.postValue(result.data.saleItems)
                is ApiResponse.Empty -> Timber.d(result.toString())
                is ApiResponse.NetworkError -> {
                    errorMessage.value = result.errorResponse
                    Timber.e(result.errorResponse)
                }
                is ApiResponse.ExceptionError -> {
                    errorMessage.value = result.errorResponse.message
                    Timber.e(result.errorResponse.toString())
                }
            }
            _isLoading.postValue(false)
        }
    }
}