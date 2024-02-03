package com.lukaslechner.coroutineusecasesonandroid.playground.intermediate_operators

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform

suspend fun main(){
    flowOf(1,2,3,4,5)
        .transform{
            emit(it * 10)
        }
        .collect{
            println(it)
        }


}