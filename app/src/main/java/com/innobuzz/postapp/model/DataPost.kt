package com.innobuzz.postapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "data")
data class DataPost(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "userId")
    var userId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "body")
    var body: String,
)

class TypeConverterDataUser {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): DataPost? {
        if (data == null) return null
        val listType: Type = object : TypeToken<DataPost?>() {}.type
        return gson.fromJson<DataPost?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObject: DataPost?): String? {
        return gson.toJson(someObject)
    }
}