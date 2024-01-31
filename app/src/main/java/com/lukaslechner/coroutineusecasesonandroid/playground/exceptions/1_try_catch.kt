package com.lukaslechner.coroutineusecasesonandroid.playground.exceptions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import kotlin.concurrent.thread

fun main(){
    val scope = CoroutineScope(Job())
    scope.launch {
        try {
            launch {
                error()
            }

        }catch (e: Exception){
            println("e: $e")
        }

    }
    Thread.sleep(100)
}

private fun error(){
    throw RuntimeException()
}