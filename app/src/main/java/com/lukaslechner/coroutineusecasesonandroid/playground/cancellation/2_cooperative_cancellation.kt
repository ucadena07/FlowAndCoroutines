package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        repeat(10){index ->
           if(isActive){
               println("Operation number $index")
               Thread.sleep(100)
           }else{
               withContext(NonCancellable){
                   println("cleaning up...")
               }
           }
        }
    }
    delay(250)
    println("Cancelling coroutine")
    job.cancel()
}