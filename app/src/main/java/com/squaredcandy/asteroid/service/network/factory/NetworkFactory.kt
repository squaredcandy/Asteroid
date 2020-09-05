package com.squaredcandy.asteroid.service.network.factory

import com.squaredcandy.asteroid.service.network.AsteroidGrpcRepository
import com.squaredcandy.asteroid.service.network.RealAsteroidGrpcRepository
import io.grpc.*
import io.grpc.okhttp.OkHttpChannelBuilder

object NetworkFactory {

    private var channel: ManagedChannel? = null

    private fun getOrCreateChannel(host: String, port: Int) : ManagedChannel {
        return channel
            ?: OkHttpChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build()
                .apply {
                    channel = this
                }
    }

    fun createAsteroidGrpcRepository(host: String, port: Int): AsteroidGrpcRepository {
        return RealAsteroidGrpcRepository()
    }
}

