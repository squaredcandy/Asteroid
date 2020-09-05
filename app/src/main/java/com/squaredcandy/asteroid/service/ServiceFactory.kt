package com.squaredcandy.asteroid.service

import android.content.Context
import com.squaredcandy.asteroid.service.credentials.factory.CredentialsFactory
import com.squaredcandy.asteroid.service.network.factory.NetworkFactory

object ServiceFactory {

    fun createService(
        context: Context,
        host: String,
        port: Int
    ): AsteroidService {
        val credentialsStore = CredentialsFactory.createCredentialsStore(context)
        val grpcRepository =
            NetworkFactory.createAsteroidGrpcRepository(
                host,
                port
            )
        return RealAsteroidService(
            credentialsStore,
            grpcRepository
        )
    }
}