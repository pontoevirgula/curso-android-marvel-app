package com.example.core.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

sealed class ResultStatus<out T> {

    object Loading : ResultStatus<Nothing>()
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Error(val throwable: Throwable) : ResultStatus<Nothing>()

    override fun toString(): String {
        return when (this) {
            Loading -> "Loading"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable=$throwable]"
        }
    }
}

interface CoroutinesDispatchers {
    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}

class AppCoroutinesDispatchers @Inject constructor() : CoroutinesDispatchers