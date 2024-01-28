package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase7

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class TimeoutAndRetryViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading
        val numberOfRetries = 2
        val timeout = 1000L



        val oreoVersionsDef = viewModelScope.async {
            retry(numberOfRetries, timeout){
                api.getAndroidVersionFeatures(27)
            }
        }
        val pieVersionsDef = viewModelScope.async {
            retry(numberOfRetries, timeout){
                api.getAndroidVersionFeatures(28)
            }
        }

        viewModelScope.launch {
            try {
                val versions = listOf(oreoVersionsDef,pieVersionsDef).awaitAll()
                uiState.value = UiState.Success(versions)

            }catch (e:Exception){
                Timber.e(e.message)
                uiState.value = UiState.Error(e.message!!)
            }
        }

    }

    private suspend fun <T> retry(
        numberOfRetries: Int,
        delayBetweenRetries: Long = 100,
        block : suspend () -> T
    ) : T{
        repeat(numberOfRetries){
            try {
                return  block()
            }catch (e: Exception){
                Timber.e(e.message)
            }
            delay(delayBetweenRetries)
        }
        return block()
    }



}