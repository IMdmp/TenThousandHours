package com.imdmpl.androidstudy.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.imdmpl.androidstudy.dependencyinjection.modules.FragmentComponent

abstract class BaseFragment : Fragment() {

    private lateinit var fragmentComponent: FragmentComponent
    private lateinit var mActivity: BaseActivity
    private var mIsInjectorUsed = false

    fun getFragmentComponent(): FragmentComponent {
        if (mIsInjectorUsed) {
            throw RuntimeException("there is no need to use injector more than once")
        }
        mIsInjectorUsed = true
        fragmentComponent = mActivity.getActivityComponent().newFragmentComponent()
        return fragmentComponent
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = activity as BaseActivity
    }
}