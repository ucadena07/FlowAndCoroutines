package com.lukaslechner.coroutineusecasesonandroid.playground.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.RuntimeException

fun main(){

    val exceptionHandler = CoroutineExceptionHandler{coroutineContext, throwable ->
        println("Caught $throwable in exception handler")
    }
    val scope = CoroutineScope(Job() + exceptionHandler)

    val deferred = scope.async {
        delay(200)
        throw RuntimeException()

    }
    scope.launch {
        deferred.await()
    }
    Thread.sleep(500)
}