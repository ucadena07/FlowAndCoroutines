package com.lukaslechner.coroutineusecasesonandroid.playground.flow_buildes

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


suspend fun main() {
    val firstFlow = flowOf<Int>(1).collect {
        println("first flow: $it")
    }

    val secondFlow = flowOf<Int>(1,2,3)

        secondFlow.collect{
        println("second flow: $it")
    }


    listOf("A","B","C").asFlow().collect{
        println("as flow: $it")
    }

    flow{
        delay(2000)
        emit("item emitted after 2000")
        emitAll(secondFlow)



    }.collect{
        println("flow {}: $it")
    }

}