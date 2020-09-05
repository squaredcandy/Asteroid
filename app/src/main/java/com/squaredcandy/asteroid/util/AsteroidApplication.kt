package com.squaredcandy.asteroid.util

import android.app.Application
import com.squaredcandy.asteroid.BuildConfig
import com.squaredcandy.asteroid.R
import com.squaredcandy.asteroid.service.ServiceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class AsteroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val hostAddress = getString(R.string.grpc_host_address)
        val hostPort = resources.getInteger(R.integer.grpc_port)

        val serviceModule = module {
            single {
                ServiceFactory.createService(this@AsteroidApplication, hostAddress, hostPort)
            }
        }

        startKoin {
            androidLogger()
            androidContext(this@AsteroidApplication)
            modules(serviceModule)
        }
    }
}