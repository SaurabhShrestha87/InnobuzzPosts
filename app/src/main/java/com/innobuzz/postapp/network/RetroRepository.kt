package com.innobuzz.postapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import com.innobuzz.postapp.db.AppDao
import com.innobuzz.postapp.model.DataPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(
    private val retroServiceInterface: RetroServiceInterface,
    private val appDao: AppDao,
) {

    fun getAllRecords(): LiveData<List<DataPost>> {
        Log.d("logging", "get all records from LiveData")
        return appDao.getAllRecords()
    }

    fun insertRecord(dataPost: DataPost) {
        Log.d("logging", "insert record to DB")
        appDao.insertRecords(dataPost)
    }

    //get data from api
    fun makeApiCall() {
        val call: Call<List<DataPost>> = retroServiceInterface.getDataFromAPI()
        call.enqueue(object : Callback<List<DataPost>> {
            override fun onResponse(
                call: Call<List<DataPost>>,
                response: Response<List<DataPost>>,
            ) {
                if (response.isSuccessful) {
                    appDao.deleteAllRecords()
                    response.body()?.forEach {
                        insertRecord(it)
                    }
                    Log.d("logging", "Success")
                }
            }

            override fun onFailure(call: Call<List<DataPost>>, t: Throwable) {
                Log.d("logging", "Failure")
            }
        })
    }
}