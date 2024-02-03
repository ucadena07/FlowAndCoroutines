package com.lukaslechner.coroutineusecasesonandroid.playground.intermediate_operators

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile

suspend fun main(){
    flowOf(1,2,3,4,5)
        .take(3)
        .collect{
            println(it)
        }
    println("=============")
    flowOf(1,2,3,4,5)
        .takeWhile { it < 3 }
        .collect{
            println(it)
        }

}