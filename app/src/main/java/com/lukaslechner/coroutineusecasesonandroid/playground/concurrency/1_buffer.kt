package com.lukaslechner.coroutineusecasesonandroid.playground.concurrency
import kotlinx.coroutines.coroutineScope
import  kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

suspend fun main() = coroutineScope {
    val flow = flow{
        kotlin.repeat(5){
            kotlin.io.println("Emitter: start cooking pancake $it")
            kotlinx.coroutines.delay(100)
            kotlin.io.println("Emitter: pancake $it ready")
            emit(it)
        }
    }.buffer()

    flow.collect{
        println("collector: start eating pancake $it")
        delay(300)
        println("collector: finished eating pancake $it")
    }

}

