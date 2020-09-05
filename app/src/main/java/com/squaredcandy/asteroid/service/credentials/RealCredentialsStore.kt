package com.squaredcandy.asteroid.service.credentials

import android.content.SharedPreferences
import androidx.core.content.edit
import com.squaredcandy.asteroid.service.credentials.models.UserCredentials

class RealCredentialsStore(
    private val preferences: SharedPreferences
): CredentialsStore {
    override fun getCredentials(): UserCredentials? {
        val jwtToken = preferences.getString(JWT_TOKEN, null) ?: return null

        return UserCredentials(
            jwtToken
        )
    }

    override fun updateUserCredentials(newCredentials: UserCredentials) {
        preferences.edit {
            putString(JWT_TOKEN, newCredentials.jwtToken)
        }
    }

    private companion object {
        const val JWT_TOKEN = "token"
    }
}