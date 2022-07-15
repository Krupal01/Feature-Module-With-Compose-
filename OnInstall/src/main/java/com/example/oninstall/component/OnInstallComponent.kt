package com.example.oninstall.component

import android.content.Context
import com.example.dynamicfeaturedemo.dynamicdependencies.OnInstallModuleDependencies
import com.example.oninstall.OnInstallActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [OnInstallModuleDependencies::class])
interface OnInstallComponent {

    fun inject(activity : OnInstallActivity)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(onInstallModuleDependencies: OnInstallModuleDependencies):Builder
        fun build() : OnInstallComponent
    }


}