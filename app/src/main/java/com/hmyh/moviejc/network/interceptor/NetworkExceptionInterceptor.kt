package com.hmyh.moviejc.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.PhoneStateListener
import android.telephony.SignalStrength
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import com.hmyh.moviejc.network.exception.NetworkException
import com.hmyh.moviejc.network.exception.NoContentException
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


/**
 * Created by PTH on 16/10/2022
 */

private const val KEY_CONTENT = 204
private const val ERROR_401 = 401

class NetworkExceptionInterceptor @Inject constructor(
    @ApplicationContext val context: Context
) : Interceptor {

    @Throws()
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        when (response.isSuccessful) {
            true -> return response
            false -> {
                when (response.code) {
                    ERROR_401 -> {
                        throw NetworkException(
                            response.body?.string(),
                            response.code
                        )
                    }
                    KEY_CONTENT -> {
                        throw NoContentException()
                    }
                    else -> {
                        throw NetworkException(
                            response.body?.string(),
                            response.code
                        )
                    }
                }
            }
        }

    }
}

object ConnectivityUtil {

    fun isConnectivityStrengthLow(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return if (activeNetwork != null) {
            when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> isWifiSignalStrengthLow(context)
                ConnectivityManager.TYPE_MOBILE -> isMobileSignalStrengthLow(context)
                else -> true
            }
        } else {
            true // Return true if no active network (i.e., connectivity is low by default)
        }
    }

    private fun isWifiSignalStrengthLow(context: Context): Boolean {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo: WifiInfo? = wifiManager.connectionInfo
        return wifiInfo?.let {
            val level = WifiManager.calculateSignalLevel(it.rssi, 5)
            level <= 1 // Consider signal low if level is 0 or 1 (out of 5)
        } ?: true // Return true if unable to get signal strength
    }

    private fun isMobileSignalStrengthLow(context: Context): Boolean {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var isLowSignal = true // Default to true if unable to get signal strength

        val listener = object : PhoneStateListener() {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onSignalStrengthsChanged(signalStrength: SignalStrength?) {
                super.onSignalStrengthsChanged(signalStrength)
                signalStrength?.let {
                    val level = it.level
                    isLowSignal = level <= 1 // Consider signal low if level is 0 or 1 (out of 4 or 5)
                }
            }
        }

        telephonyManager.listen(listener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS)

        return isLowSignal
    }
}