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
                retry(numberOfRetries){
                    loadRecentVersions()
                }
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

    private suspend fun <T> retry(numberOfRetries: Int, block: suspend () -> T) : T{
        repeat(numberOfRetries){
            try {
               return block()
            }catch (e: Exception){
                Timber.e(e)
            }
        }
        return block()
    }

}