package com.imdmpl.androidstudy.dependencyinjection.modules

import com.imdmpl.androidstudy.dependencyinjection.scopes.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {
    fun newFragmentComponent(): FragmentComponent

}