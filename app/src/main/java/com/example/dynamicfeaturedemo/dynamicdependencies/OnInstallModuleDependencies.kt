package com.example.dynamicfeaturedemo.dynamicdependencies

import com.example.dynamicfeaturedemo.data.DataClass
import com.example.dynamicfeaturedemo.module.VMFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface OnInstallModuleDependencies {

    fun getDataClass() : DataClass
    fun getVMFactory() : VMFactory

}