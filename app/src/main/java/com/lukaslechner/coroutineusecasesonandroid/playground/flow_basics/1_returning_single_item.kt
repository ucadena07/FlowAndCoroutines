package com.lukaslechner.coroutineusecasesonandroid.playground.flow_basics

import java.math.BigInteger

fun main(){
    var result = calculateFactorialOf(5)
    println("Result: $result")
}

private fun calculateFactorialOf(number: Int) : BigInteger{
    var factorial= BigInteger.ONE
    for (i in 1..number){
        Thread.sleep(10)
        factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
    }
    return factorial
}