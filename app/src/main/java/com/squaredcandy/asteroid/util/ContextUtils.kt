package com.squaredcandy.asteroid.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import com.squaredcandy.asteroid.util.NetworkUtils.hasWifiTransport
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun Context.connectedToWifiFlow(): Flow<Boolean> {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()

    return callbackFlow {
        if(connectivityManager.hasWifiTransport()) {
            offer(true)
        } else {
            offer(false)
        }

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                offer(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                offer(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                offer(false)
            }
        }

        connectivityManager.registerNetworkCallback(networkRequest, callback)
        awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
    }
}

fun Context.isConnectedToWifi(): Boolean {
    val wifiManager = getSystemService(WifiManager::class.java)
    return wifiManager?.isWifiEnabled == true
}

fun Context.tryGetUsername(): String {
    return Settings.System.getString(contentResolver, "bluetooth_name")
        ?: Settings.Secure.getString(contentResolver, "bluetooth_name")
        ?: Settings.System.getString(contentResolver, "device_name")
        ?: Build.MODEL
}

fun Context.getMacAddress(): String? {
    return NetworkUtils.getWifiMacAddress() ?: run {
        val wifiManager = getSystemService(WifiManager::class.java)
        return@run wifiManager.connectionInfo.macAddress
    }
}

sealed class ConnectionType {
    object WIFI : ConnectionType()
    object MOBILE : ConnectionType()
    object ETHERNET : ConnectionType()
}

fun Context.getConnectionType() : ConnectionType? {
    if(isConnectedToWifi()) return ConnectionType.WIFI

    val connectivityManager = getSystemService(ConnectivityManager::class.java) ?: return null
    val network = connectivityManager.activeNetwork ?: return null

    val cap = connectivityManager.getNetworkCapabilities(network) ?: return null
    if(!(cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))) return null

    if(cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) return ConnectionType.MOBILE
    if(cap.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) return ConnectionType.ETHERNET
    if(cap.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
        if(connectivityManager.isActiveNetworkMetered) {
            return ConnectionType.MOBILE
        }
    }

    return null
}

fun isEmulator(): Boolean {
    return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
            || Build.FINGERPRINT.startsWith("generic")
            || Build.FINGERPRINT.startsWith("unknown")
            || Build.HARDWARE.contains("goldfish")
            || Build.HARDWARE.contains("ranchu")
            || Build.MODEL.contains("google_sdk")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.MANUFACTURER.contains("Genymotion")
            || Build.PRODUCT.contains("sdk_google")
            || Build.PRODUCT.contains("google_sdk")
            || Build.PRODUCT.contains("sdk")
            || Build.PRODUCT.contains("sdk_x86")
            || Build.PRODUCT.contains("vbox86p")
            || Build.PRODUCT.contains("emulator")
            || Build.PRODUCT.contains("simulator"))
}