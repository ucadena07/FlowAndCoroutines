package com.lukaslechner.coroutineusecasesonandroid.playground.structureConcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(){
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    var childCoroutineJob: Job? = null
    val coroutineJob = scope.launch {
        childCoroutineJob = launch {
            println("stating child coroutine")
            delay(1000)
        }


        println("starting coroutine")
        delay(1000)
    }

    Thread.sleep(1000)
    println("is childCoroutineJob a child of coroutineJob => ${coroutineJob.children.contains(childCoroutineJob)}")
    println("is coroutine a child of scopeJob => ${scopeJob.children.contains(coroutineJob)}")
}