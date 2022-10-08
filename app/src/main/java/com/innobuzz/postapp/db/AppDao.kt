package com.innobuzz.postapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.innobuzz.postapp.model.DataPost

@Dao
interface AppDao {

    @Query("SELECT * FROM data")
    fun getAllRecords(): LiveData<List<DataPost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(dataPost: DataPost)

    @Query("DELETE FROM data")
    fun deleteAllRecords()

    @Query("SELECT * FROM data WHERE id = :id")
    fun getDataUserById(id: Int): DataPost

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateRecord(dataPost: DataPost)

    @Delete
    fun deleteRecord(dataPost: DataPost)
}