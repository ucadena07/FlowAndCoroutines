package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class PerformNetworkRequestsConcurrentlyViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading
        viewModelScope.launch{
            try {
                val oreoFeatures = mockApi.getAndroidVersionFeatures(27)
                val pieFeature =  mockApi.getAndroidVersionFeatures(28)
                val android10Feature =  mockApi.getAndroidVersionFeatures(29)

                val versionFeatures = listOf(oreoFeatures,pieFeature,android10Feature)
                uiState.value = UiState.Success(versionFeatures)
            }catch (e: Exception){
                uiState.value = UiState.Error(e.message!!)
            }

        }
    }

    fun performNetworkRequestsConcurrently() {

            val oreoFeaturesDef = viewModelScope.async {
                mockApi.getAndroidVersionFeatures(27)
            }
            val pieFeatureDef = viewModelScope.async {
                mockApi.getAndroidVersionFeatures(28)
            }
            val android10FeatureDef = viewModelScope.async {
                mockApi.getAndroidVersionFeatures(29)
            }
            viewModelScope.launch {
                try {
//                    val oreoFeatures = oreoFeaturesDef.await()
//                    val pieFeature = pieFeatureDef.await()
//                    val android10Feature = android10FeatureDef.await()
//                    val versionFeatures = listOf(oreoFeatures, pieFeature, android10Feature)
                    val versionFeatures = awaitAll(oreoFeaturesDef, pieFeatureDef, android10FeatureDef)
                    uiState.value = UiState.Success(versionFeatures)
                }catch (e: Exception){
                    uiState.value = UiState.Error(e.message!!)
                }
            }

    }
}