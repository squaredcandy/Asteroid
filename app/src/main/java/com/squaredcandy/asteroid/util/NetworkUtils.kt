package com.squaredcandy.asteroid.util

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.net.Inet4Address
import java.net.NetworkInterface


object NetworkUtils {
    fun getWifiMacAddress(): String? {
        return try {
            val interfaceName = "wlan0"
            NetworkInterface.getNetworkInterfaces().asSequence().mapNotNull { networkInterface ->
                if(!networkInterface.name.equals(interfaceName, ignoreCase = true)) return@mapNotNull null
                val macAddressArray = networkInterface.hardwareAddress ?: return@mapNotNull null
                val macAddressBuilder = macAddressArray.fold(StringBuilder()) { acc: StringBuilder, byte: Byte ->
                    acc.append(String.format("%02X:", byte))
                    return@fold acc
                }
                if(macAddressBuilder.isNotEmpty()) {
                    macAddressBuilder.deleteCharAt(macAddressBuilder.lastIndex)
                }
                return@mapNotNull macAddressBuilder.toString()
            }.firstOrNull()
        } catch (t: Throwable) {
            null
        }
    }

    fun getIpAddress(): String? {
        return try {
            val inetAddress = NetworkInterface.getNetworkInterfaces().asSequence().flatMap { networkInterface ->
                networkInterface.inetAddresses.asSequence()
            }.firstOrNull { inetAddress ->
                !inetAddress.isLoopbackAddress && inetAddress is Inet4Address
            }
            inetAddress?.hostAddress
        } catch (t: Throwable) {
            if(isEmulator()) "10.0.2.2"
             else null
        }
    }


    fun NetworkCapabilities.hasWifiTransport(): Boolean {
        return hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }

    fun NetworkCapabilities.hasVpnTransport(): Boolean {
        return hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }

    fun ConnectivityManager.hasWifiTransport(): Boolean {
        val currentNetwork = activeNetwork ?: return false
        val networkCapabilities = getNetworkCapabilities(currentNetwork) ?: return false
        return networkCapabilities.hasWifiTransport() || (networkCapabilities.hasVpnTransport() && !isActiveNetworkMetered)
    }
}