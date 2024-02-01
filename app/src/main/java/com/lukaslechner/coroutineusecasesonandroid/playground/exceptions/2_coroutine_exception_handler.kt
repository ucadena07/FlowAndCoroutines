package com.lukaslechner.coroutineusecasesonandroid.playground.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.RuntimeException

fun main(){
    val exceptionHandler = CoroutineExceptionHandler{coroutineContext, throwable ->
        println("Caught $throwable in exception handler")
    }
    val scope = CoroutineScope(Job() + exceptionHandler)
    scope.launch {
        throw RuntimeException()

    }
    Thread.sleep(100)
}