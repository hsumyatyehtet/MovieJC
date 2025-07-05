package com.hmyh.moviejc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MovieJCApp: Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize any libraries or SDKs here if needed
        // For example, you can initialize logging, crash reporting, etc.

        // log
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return String.format(
                    "C:%s, L:%s",
                    super.createStackElementTag(element)?.substringBefore("$")
                        ?: getString(R.string.str_timber),
                    element.lineNumber
//                        element.methodName
                )
            }
        })
    }
}