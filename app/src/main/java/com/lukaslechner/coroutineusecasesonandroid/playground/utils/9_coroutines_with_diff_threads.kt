package com.lukaslechner.coroutineusecasesonandroid.playground.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { coroutineWithDiffThreads(1,500) },
        async { coroutineWithDiffThreads(2,300) }
    )
}

suspend fun coroutineWithDiffThreads(number: Int, delay: Long){
    println("Routine $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    withContext(Dispatchers.Default){
        println("Routine $number has finished on ${Thread.currentThread().name}")
    }

}