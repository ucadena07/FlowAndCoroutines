package com.lukaslechner.coroutineusecasesonandroid.playground.terminal_operators

import kotlinx.coroutines.flow.flow

fun main(){
    val flow = flow{
        kotlinx.coroutines.delay(100)
        println("emitting first value")
        emit(1)

        kotlinx.coroutines.delay(100)
        println("emitting second value")
        emit(2)
    }


    val list = buildList {
        add(1)
        println("add 1 to list")
        add(2)
        println("add 2 to list")
    }
}
