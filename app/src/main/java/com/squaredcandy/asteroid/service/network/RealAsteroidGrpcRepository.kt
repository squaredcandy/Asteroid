package com.squaredcandy.asteroid.service.network

class RealAsteroidGrpcRepository(
) : AsteroidGrpcRepository {
    override suspend fun registerUser(username: String, password: String): Boolean {
//        return registerService.register(username, password).fold({
            return true
//        }) {
//            false
//        }
    }
}