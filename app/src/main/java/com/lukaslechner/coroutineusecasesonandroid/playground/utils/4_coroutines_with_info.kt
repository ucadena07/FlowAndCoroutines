package com.lukaslechner.coroutineusecasesonandroid.playground.utils

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { coroutineWithInfo(1,500) },
        async { coroutineWithInfo(2,300) }
    )
}

suspend fun coroutineWithInfo(number: Int, delay: Long){
    println("Routine $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    println("Routine $number has finished on ${Thread.currentThread().name}")
}