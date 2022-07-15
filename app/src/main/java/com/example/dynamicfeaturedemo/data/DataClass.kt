package com.example.dynamicfeaturedemo.data

class DataClass {

    private val dataList = arrayListOf<String>()

    fun getData(): ArrayList<String> {
        return dataList
    }

    fun getDataCount(): Int {
        return dataList.size
    }

    init {
        for (i in 0..5){
            dataList.add("item $i")
        }
    }

}