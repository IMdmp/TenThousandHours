package com.imdmpl.androidstudy.dependencyinjection.modules

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    fun getFragmentManager(): FragmentManager {
        return mActivity.supportFragmentManager
    }

    @Provides
    fun getLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(mActivity)
    }

    @Provides
    fun getActivity(): Activity {
        return mActivity
    }

    @Provides
    fun getAppCompatActivity(): AppCompatActivity {
        return mActivity
    }


}