package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: CallbackMockApi = mockApi()
) : BaseViewModel<UiState>() {

    private  var getAndroidVersions: Call<List<AndroidVersion>>? = null
    private  var getAndroidFeatures: Call<VersionFeatures>? = null
    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading

        getAndroidVersions = mockApi.getRecentAndroidVersions()
        getAndroidVersions!!.enqueue(object: Callback<List<AndroidVersion>>{
            override fun onResponse(
                call: Call<List<AndroidVersion>>,
                response: Response<List<AndroidVersion>>
            ) {
                if(response.isSuccessful){
                    val version =  response.body()!!.last()
                    getAndroidFeatures = mockApi.getAndroidVersionFeatures(version.apiLevel)
                    getAndroidFeatures!!.enqueue(object : Callback<VersionFeatures>{
                        override fun onResponse(
                            call: Call<VersionFeatures>,
                            response: Response<VersionFeatures>
                        ) {
                           if(response.isSuccessful){
                                val features = response.body()!!
                               uiState.value = UiState.Success(features)
                           }else{
                               uiState.value = UiState.Error("Network request failed")
                           }
                        }

                        override fun onFailure(call: Call<VersionFeatures>, t: Throwable) {
                            uiState.value = UiState.Error("Something went wrong.")
                        }

                    })
                }else{
                    uiState.value = UiState.Error("Network request failed")
                }
            }

            override fun onFailure(call: Call<List<AndroidVersion>>, t: Throwable) {
               uiState.value = UiState.Error("Something went wrong.")
            }

        })
    }
    override fun onCleared(){
        super.onCleared()
        getAndroidVersions?.cancel()
        getAndroidFeatures?.cancel()
    }
}