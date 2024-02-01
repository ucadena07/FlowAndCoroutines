package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase13

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import kotlinx.coroutines.*
import timber.log.Timber
import java.sql.Time

class ExceptionHandlingViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun handleExceptionWithTryCatch() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                api.getAndroidVersionFeatures(27)
            }catch (e: Exception){
                uiState.value = UiState.Error("Network request failed: ${e}")
            }

        }
    }

    fun handleWithCoroutineExceptionHandler() {
        uiState.value = UiState.Loading
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            uiState.value = UiState.Error("Network request failed")
        }
        viewModelScope.launch(exceptionHandler) {
            api.getAndroidVersionFeatures(27)
        }
    }

    fun showResultsEvenIfChildCoroutineFails() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            supervisorScope {
                val oreo = async {
                    api.getAndroidVersionFeatures(27)
                }
                val pie = async {
                    api.getAndroidVersionFeatures(28)
                }
                val android10 = async {
                    api.getAndroidVersionFeatures(29)
                }
                val versionFeatures = listOf(oreo, pie, android10).mapNotNull {
                    try {
                        it.await()
                    } catch (e: Exception) {
                        if(e is CancellationException){
                            throw e
                        }
                        Timber.e("error loading feature data")
                        null
                    }
                }

                uiState.value = UiState.Success(versionFeatures)
            }

        }
    }
}