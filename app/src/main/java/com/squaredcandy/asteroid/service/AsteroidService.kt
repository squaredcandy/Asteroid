package com.squaredcandy.asteroid.service

interface AsteroidService {
    /**
     * Registers the user to the server. Returns true if succeeded
     */
    suspend fun registerUser(username: String, password: String): Boolean
}