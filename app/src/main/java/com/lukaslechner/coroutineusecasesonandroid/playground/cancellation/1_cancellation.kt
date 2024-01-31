package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(10){index ->
            println("Operation number $index")
            try {
                delay(100)
            }catch (e: Exception){
                println("cancellation was thrown")
                throw CancellationException()
            }

        }
    }
    delay(250)
    println("Cancelling coroutine")
    job.cancel()
}