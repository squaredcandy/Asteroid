package com.squaredcandy.asteroid.service.credentials.factory

import android.content.Context
import com.squaredcandy.asteroid.service.credentials.CredentialsStore
import com.squaredcandy.asteroid.service.credentials.RealCredentialsStore

object CredentialsFactory {

    fun createCredentialsStore(context: Context): RealCredentialsStore {
        val sharedPreferences = context.getSharedPreferences(
            CredentialsStore.CREDENTIALS,
            Context.MODE_PRIVATE
        )
        return RealCredentialsStore(sharedPreferences)
    }
}