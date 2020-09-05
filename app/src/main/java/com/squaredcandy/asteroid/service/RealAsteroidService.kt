package com.squaredcandy.asteroid.service

import com.squaredcandy.asteroid.service.network.AsteroidGrpcRepository
import com.squaredcandy.asteroid.service.credentials.CredentialsStore
import com.squaredcandy.asteroid.service.credentials.withCredentials

class RealAsteroidService(
    private val credentials: CredentialsStore,
    private val grpc: AsteroidGrpcRepository
): AsteroidService {
    override suspend fun registerUser(username: String, password: String): Boolean {
        return grpc.registerUser(username, password)
    }
}