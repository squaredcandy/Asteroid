package com.squaredcandy.asteroid.util

/**
 * Simple way to convert something that can fail into a [Result] suspending version
 */
suspend fun <T : Any> suspendGetResult(block: suspend () -> T): Result<T> {
    return try {
        val result = block()
        Result.Success(result)
    } catch (t: Throwable) {
        Result.Failure(t)
    }
}

suspend fun <T : Any, U> Result<T>.suspendFold(onSuccess: suspend (T) -> U, onFailure: suspend (Throwable) -> U): U {
    return when(this) {
        is Result.Success -> onSuccess(value)
        is Result.Failure -> onFailure(t)
    }
}

suspend fun <T : Any> Result<T>.suspendOnSuccess(onSuccess: suspend (T) -> Unit) {
    if(this is Result.Success) {
        onSuccess(value)
    }
}

suspend fun <T : Any> Result<T>.suspendOnFailure(onFailure: suspend (Throwable) -> Unit) {
    if(this is Result.Failure) {
        onFailure(t)
    }
}