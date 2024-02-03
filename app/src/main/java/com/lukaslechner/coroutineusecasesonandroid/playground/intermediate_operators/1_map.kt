package com.lukaslechner.coroutineusecasesonandroid.playground.intermediate_operators

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

suspend fun main(){
    flowOf(1,2,3,4,5)
        .map { it * 10 }
        .collect{
            println(it)
        }

}