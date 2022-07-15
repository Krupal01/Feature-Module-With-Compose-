package com.example.oninstall.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.dynamicfeaturedemo.data.DataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class OnInstallViewModel @Inject constructor(
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