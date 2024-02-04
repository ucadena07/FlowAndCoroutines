package com.lukaslechner.coroutineusecasesonandroid.playground.flow_cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main(){
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        intFlow()
            .onCompletion {throwable ->
                if(throwable is CancellationException){
                    println("cancelled")
                }
            }.collect{
                println("collected $it")
                if(it == 2){
                    cancel()
                }
            }
    }.join()
}

private fun intFlow() = flow{
    emit(1)
    emit(2)
    emit(3)
}