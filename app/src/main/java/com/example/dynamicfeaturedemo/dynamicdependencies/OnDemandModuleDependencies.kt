package com.example.dynamicfeaturedemo.dynamicdependencies

import com.example.dynamicfeaturedemo.data.DataClass
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface OnDemandModuleDependencies {

    fun getDataClass(): DataClass
}