package com.lukaslechner.coroutineusecasesonandroid.playground.flow_basics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import java.math.BigInteger

fun main(){
    val start = System.currentTimeMillis()
    calculateFactorialOf(5).forEach{
        printWithTimePassed(it,start)
    }
}

private fun calculateFactorialOf(number: Int) : List<BigInteger> = buildList{
    var factorial= BigInteger.ONE
    for (i in 1..number){
        Thread.sleep(10)
        factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        add(factorial)
    }

}