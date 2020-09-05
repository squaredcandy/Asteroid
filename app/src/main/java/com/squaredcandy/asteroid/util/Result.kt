package com.squaredcandy.asteroid.util

/**
 * Basic implementation of a success/failure result
 */
sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val t: Throwable) : Result<Nothing>()

    fun getSuccess(): T = when (this) {
        is Success -> value
        is Failure -> throw Throwable("Expected Success")
    }

    fun getFailure(): Throwable = when (this) {
        is Success -> throw Throwable("Expected Failure")
        is Failure -> t
    }
}