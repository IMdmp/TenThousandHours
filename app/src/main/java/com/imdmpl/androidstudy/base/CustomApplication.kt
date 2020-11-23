package com.imdmpl.androidstudy.base

import android.app.Application
import com.imdmpl.androidstudy.BuildConfig
import com.imdmpl.androidstudy.dependencyinjection.ApplicationComponent
import com.imdmpl.androidstudy.dependencyinjection.DaggerApplicationComponent
import timber.log.Timber

class CustomApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun getAppComponent(): ApplicationComponent {
        if (!::applicationComponent.isInitialized) {
            applicationComponent = DaggerApplicationComponent.builder()
                .build()
        }
        return applicationComponent
    }

}