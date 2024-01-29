package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase10

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class CalculationInBackgroundViewModel : BaseViewModel<UiState>() {

    fun performCalculation(factorialOf: Int) {
        uiState.value = UiState.Loading
        viewModelScope.launch{
            var result: BigInteger = BigInteger.ONE
            var resultString= ""
            val computationDuration = measureTimeMillis {
                result = calculateFactorialOf(factorialOf)
            }
            val stringConversionDuration = measureTimeMillis {
                resultString = withContext(Dispatchers.Default){ result.toString()}
            }


                uiState.value = UiState.Success(resultString,computationDuration,stringConversionDuration)


        }

    }

    private suspend fun calculateFactorialOf(number: Int) :BigInteger{
        return withContext(Dispatchers.Default){
            var factorial = BigInteger.ONE
            for (i in 1..number){
                factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
            }
             factorial
        }

    }
}