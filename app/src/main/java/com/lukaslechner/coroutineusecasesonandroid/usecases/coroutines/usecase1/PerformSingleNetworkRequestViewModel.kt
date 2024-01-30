package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import timber.log.Timber

class PerformSingleNetworkRequestViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performSingleNetworkRequest() {
        uiState.value = UiState.Loading
        Timber.d("I am the first statement in the coroutine")
        val job = viewModelScope.launch {
            try {
                val versions = mockApi.getRecentAndroidVersions()
                uiState.value = UiState.Success(versions)
            }catch (e: Exception){
                Timber.e(e)
                uiState.value = UiState.Error(e.message!!)
            }

        }
        Timber.d("I am the first statement after launching the coroutine")
        job.invokeOnCompletion {
            if(it is CancellationException){
                Timber.e("Coroutine was cancelled.")
            }
        }

    }
}