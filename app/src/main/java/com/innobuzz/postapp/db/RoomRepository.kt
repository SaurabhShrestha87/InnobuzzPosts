package com.innobuzz.postapp.db

import com.innobuzz.postapp.model.DataPost
import javax.inject.Inject

class RoomRepository @Inject constructor(private val appDao: AppDao) {

    fun insertRecords(dataPost: DataPost) {
        appDao.insertRecords(dataPost)
    }

    fun getDataUserById(id: Int): DataPost {
        return appDao.getDataUserById(id)
    }

    fun updateRecord(dataPost: DataPost) {
        appDao.updateRecord(dataPost)
    }

    fun deleteRecord(dataPost: DataPost) {
        appDao.deleteRecord(dataPost)
    }
}