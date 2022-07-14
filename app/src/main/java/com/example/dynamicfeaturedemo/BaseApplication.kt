package com.example.dynamicfeaturedemo

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}