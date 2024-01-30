package com.lukaslechner.coroutineusecasesonandroid.playground.structureConcurrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.RuntimeException

fun main(){
    val exceptionHandler = CoroutineExceptionHandler{coroutineContext, throwable ->
        println("caught exception $throwable")
    }
    val scope = CoroutineScope(Job() + exceptionHandler)
    scope.launch {
        println("coroutine 1 stars")
        delay(50)
        println("coroutine 1 fails")
        throw  RuntimeException()
    }
    scope.launch {
        println("coroutine 2 starts")
        delay(500)
        println("coroutine 2 completed")
    }.invokeOnCompletion {
        if(it is CancellationException){
            println("coroutine 2 got cancelled")
        }
    }

    Thread.sleep(1000)
}