package com.lukaslechner.coroutineusecasesonandroid.playground.concurrency
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import  kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

suspend fun main() = coroutineScope {
    val flow = flow{
        kotlin.repeat(5){
            val pancake = it + 1
            kotlin.io.println("Emitter: start cooking pancake $pancake")
            kotlinx.coroutines.delay(100)
            kotlin.io.println("Emitter: pancake $pancake ready")
            emit(pancake)
        }
    }.buffer(capacity = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    flow.collect{
        println("collector: start eating pancake $it")
        delay(300)
        println("collector: finished eating pancake $it")
    }

}

