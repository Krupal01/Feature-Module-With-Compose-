package com.example.ondemand.component

import android.content.Context
import com.example.dynamicfeaturedemo.dynamicdependencies.OnDemandModuleDependencies
import com.example.dynamicfeaturedemo.dynamicdependencies.OnInstallModuleDependencies
import com.example.ondemand.OnDemandActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

@Component(dependencies = [OnDemandModuleDependencies::class])
interface OnDemandComponent {

    fun inject(activity : OnDemandActivity)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context):Builder
        fun appDependencies(onDemandModuleDependencies: OnDemandModuleDependencies):Builder
        fun build(): OnDemandComponent
    }
}