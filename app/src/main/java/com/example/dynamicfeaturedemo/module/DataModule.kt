package com.example.dynamicfeaturedemo.module

import com.example.dynamicfeaturedemo.data.DataClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun getDataClass() : DataClass {
        return DataClass()
    }

}