package com.squaredcandy.asteroid.util


/**
 * Simple way to convert something that can fail into a [Result] non-suspending version
 */
fun <T : Any> getResult(block: () -> T): Result<T> {
    return try {
        val result = block()
        Result.Success(result)
    } catch (t: Throwable) {
        Result.Failure(t)
    }
}

fun <T : Any, U> Result<T>.fold(onSuccess: (T) -> U, onFailure: (Throwable) -> U): U {
    return when(this) {
        is Result.Success -> onSuccess(value)
        is Result.Failure -> onFailure(t)
    }
}

fun <T : Any> Result<T>.onSuccess(onSuccess: (T) -> Unit) {
    if(this is Result.Success) {
        onSuccess(value)
    }
}

fun <T : Any> Result<T>.onFailure(onFailure: (Throwable) -> Unit) {
    if(this is Result.Failure) {
        onFailure(t)
    }
}