package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase6

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.launch
import timber.log.Timber

class RetryNetworkRequestViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            val numberOfRetries = 2
            try {
                repeat(numberOfRetries){
                    try{
                        loadRecentVersions()
                        return@launch
                    }catch (e: Exception){
                        Timber.e(e)
                    }
                }
                loadRecentVersions()
            }catch (e: Exception){
                Timber.e(e)
                uiState.value = UiState.Error(e.message!!)
            }

        }
    }

    private suspend fun loadRecentVersions() {
        val versions = api.getRecentAndroidVersions()
        uiState.value = UiState.Success(versions)
    }

}