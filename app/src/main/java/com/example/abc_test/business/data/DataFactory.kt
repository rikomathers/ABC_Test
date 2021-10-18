package com.example.abc_test.business.data

import com.example.abc_test.business.domain.model.DataItem
import java.lang.Exception

object DataFactory {

    val list = listOf<DataItem>(
        DataItem(
            id = "id1",
            name = "name1",
            isFavorite = false
        ),
        DataItem(
            id = "id2",
            name = "name2",
            isFavorite = false
        ),
        DataItem(
            id = "id3",
            name = "name3",
            isFavorite = false
        )
    )

    fun getData(): List<DataItem> = list

    fun getDataByNumber(number: Int ) = try {
        list[number]
    } catch (e: Exception) {
        list[0]
    }

}