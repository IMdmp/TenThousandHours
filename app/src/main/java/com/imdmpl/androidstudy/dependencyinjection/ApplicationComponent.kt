package com.imdmpl.androidstudy.dependencyinjection

import com.imdmpl.androidstudy.dependencyinjection.modules.ActivityComponent
import com.imdmpl.androidstudy.dependencyinjection.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
    ]
)
interface ApplicationComponent {
    fun newActivityComponent(): ActivityComponent

}