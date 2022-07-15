package com.example.dynamicfeaturedemo.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dynamicfeaturedemo.data.DataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataClass: DataClass
) :ViewModel() {
    val count = mutableStateOf(0)

    fun getCount(){
        count.value = dataClass.getDataCount()
    }

    init {
        getCount()
    }
}