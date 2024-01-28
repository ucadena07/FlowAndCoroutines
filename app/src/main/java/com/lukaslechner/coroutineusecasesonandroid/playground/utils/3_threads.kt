package com.lukaslechner.coroutineusecasesonandroid.playground.utils

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() = runBlocking {
    println("main starts")
    threadRoutine(1,500)
    threadRoutine(2,300)
    Thread.sleep(1000)
    println("main finished")
}

 fun threadRoutine(number: Int, delay: Long){
     thread{
         println("Routine $number starts work")
         Thread.sleep(delay)
         println("Routine $number has finished")
     }


}