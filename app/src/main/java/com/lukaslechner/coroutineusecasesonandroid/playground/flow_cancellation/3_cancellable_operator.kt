package com.lukaslechner.coroutineusecasesonandroid.playground.flow_cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main(){
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        flowOf(1,2,3)
            .onCompletion {throwable ->
                if(throwable is CancellationException){
                    println("cancelled")
                }
            }.cancellable()
            .collect{
                println("collected $it")
                if(it == 2){
                    cancel()
                }
            }
    }.join()
}

