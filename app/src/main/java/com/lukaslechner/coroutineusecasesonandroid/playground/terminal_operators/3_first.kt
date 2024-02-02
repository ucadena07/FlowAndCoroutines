package com.lukaslechner.coroutineusecasesonandroid.playground.terminal_operators

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main(){
    val flow = flow{
        kotlinx.coroutines.delay(100)
        println("emitting first value")
        emit(1)

        kotlinx.coroutines.delay(100)
        println("emitting second value")
        emit(2)
    }

    runBlocking {
        val item = flow.first()
        println(item)

    }

}
