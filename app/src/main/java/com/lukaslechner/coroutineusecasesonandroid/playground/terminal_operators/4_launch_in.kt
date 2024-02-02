package com.lukaslechner.coroutineusecasesonandroid.playground.terminal_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext

fun main(){
    val flow = flow{
        kotlinx.coroutines.delay(100)
        println("emitting first value")
        emit(1)

        kotlinx.coroutines.delay(100)
        println("emitting second value")
        emit(2)
    }

    val scope = CoroutineScope(EmptyCoroutineContext)
    flow.onEach { println("received $it") }.launchIn(scope)

    scope.launch {
        flow.collect{
            println(it)
        }
    }

    Thread.sleep(1000)

}
