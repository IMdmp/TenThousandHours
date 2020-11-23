package com.imdmpl.androidstudy.base

import androidx.appcompat.app.AppCompatActivity
import com.imdmpl.androidstudy.dependencyinjection.ApplicationComponent
import com.imdmpl.androidstudy.dependencyinjection.modules.ActivityComponent

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var activityComponent: ActivityComponent


    fun getActivityComponent(): ActivityComponent {

        if (::activityComponent.isInitialized) {
            activityComponent = getApplicationComponent().newActivityComponent()
        }
        return activityComponent
    }

    fun getApplicationComponent(): ApplicationComponent {
        val application = getApplication() as CustomApplication
        return application.getAppComponent()
    }
}