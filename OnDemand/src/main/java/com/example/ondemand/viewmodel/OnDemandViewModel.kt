package com.example.ondemand.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.dynamicfeaturedemo.data.DataClass
import javax.inject.Inject

class OnDemandViewModel @Inject constructor(
    private val dataClass: DataClass
) : ViewModel() {

    var dataList = mutableStateListOf<String>()

    fun getDataList(){
        dataList.clear()
        dataList.addAll(dataClass.getData())
    }

    init {
        getDataList()
    }
}