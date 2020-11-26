package com.exercise.firstdigitalbank.tmdb.util

import android.net.NetworkInfo
import android.telephony.TelephonyManager
import com.exercise.firstdigitalbank.tmdb.data.model.ConnectionType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtils @Inject constructor(private val networkInfo: NetworkInfo?, private val telephonyManager: TelephonyManager?) {

    fun isNetworkAvailable() : Boolean = networkInfo?.isConnected ?: false

    fun getActiveNetworkName() : String = getConnectionType().connectionName

    private fun getConnectionType() : ConnectionType =
        when (networkInfo) {
            null -> ConnectionType.OFFLINE
            else -> ConnectionType.fromValue(networkInfo.type)
        }

    fun getNetworkOperatorName(): String = telephonyManager?.networkOperatorName?: "unknown"
}