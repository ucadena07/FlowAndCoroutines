package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase5

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber

class NetworkRequestWithTimeoutViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest(timeout: Long) {
        uiState.value = UiState.Loading
        usingWithTimeoutOrNull(timeout)
    }

    private fun usingWithTimeout(timeout: Long) {
        viewModelScope.launch {
            try {

                val versions = withTimeout(timeout) {
                    api.getRecentAndroidVersions()
                }


                uiState.value = UiState.Success(versions)
            } catch (timeoutCancellationException: TimeoutCancellationException) {
                uiState.value = UiState.Error(timeoutCancellationException.message!!)
            } catch (e: Exception) {
                Timber.e(e)
                uiState.value = UiState.Error(e.message!!)
            }

        }
    }
    private fun usingWithTimeoutOrNull(timeout: Long) {
        viewModelScope.launch {
            try {

                val versions = withTimeoutOrNull(timeout) {
                    api.getRecentAndroidVersions()
                }

                if(versions != null){
                    uiState.value = UiState.Success(versions)
                }else{
                    uiState.value = UiState.Error("timeout error")
                }

            }  catch (e: Exception) {
                Timber.e(e)
                uiState.value = UiState.Error(e.message!!)
            }

        }
    }

}