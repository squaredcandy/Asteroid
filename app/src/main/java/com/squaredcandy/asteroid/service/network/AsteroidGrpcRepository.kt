package com.squaredcandy.asteroid.service.network

interface AsteroidGrpcRepository {
    suspend fun registerUser(username: String, password: String): Boolean
}