package com.lukaslechner.coroutineusecasesonandroid.playground.flow_exception_handling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main() : Unit = coroutineScope {
    launch {
        val stockFlow = stockFlow()
        try {
            stockFlow
                .onCompletion {cause ->
                    if(cause == null){
                        println("completed successfully")
                    }else{
                        println("completed with errors: ${cause.localizedMessage}")
                    }
                }
                .collect{
                    println(it)
                }
        }catch (e: Exception){
            println("exception handled")
        }

    }
}

fun stockFlow(): Flow<String> = flow{
    emit("Apple")
    throw  Exception("network request failed")
    emit("Microsoft")
}
