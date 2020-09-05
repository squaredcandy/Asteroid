package com.squaredcandy.asteroid.service.credentials

import com.squaredcandy.asteroid.service.credentials.models.UserCredentials

interface CredentialsStore {
    fun getCredentials(): UserCredentials?
    fun updateUserCredentials(newCredentials: UserCredentials)

    companion object {
        const val CREDENTIALS = "credentials"
    }
}

fun <T> CredentialsStore.withCredentials(block: (UserCredentials) -> T?): T? {
    val credentials = getCredentials() ?: return null
    return block(credentials)
}