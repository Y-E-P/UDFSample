package com.pou.udfsample

import android.app.Application
import com.pou.udfsample.ui.main.ServiceLocator

class FruitApp : Application() {

    override fun onCreate() {
        ServiceLocator.context = this
        super.onCreate()
    }
}