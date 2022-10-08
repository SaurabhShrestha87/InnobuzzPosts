package com.innobuzz.postapp.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.innobuzz.postapp.internal.Event
import com.innobuzz.postapp.db.RoomRepository
import com.innobuzz.postapp.model.DataPost
import com.innobuzz.postapp.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val retroRepository: RetroRepository,
    private val roomRepository: RoomRepository,
) :
    ViewModel() {

    fun getDataUserById(id: Int): DataPost {
        return roomRepository.getDataUserById(id)
    }

    fun updateRecord(dataPost: DataPost) {
        roomRepository.updateRecord(dataPost)
    }

    fun deleteRecord(dataPost: DataPost) {
        roomRepository.deleteRecord(dataPost)
    }

    fun getAllRepositoryList(): LiveData<List<DataPost>> {
        return retroRepository.getAllRecords()
    }

    fun makeApiCall() {
        retroRepository.makeApiCall()
    }

    /** Error handling as UI **/
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _showToast = MutableLiveData<Event<Any>>()
    val showToast: LiveData<Event<Any>> get() = _showToast

    fun showToastMessage(msg : String) {
        _showToast.value = Event(msg)
    }

    // Quantity of cupcakes in this order
    private val _id = MutableLiveData<Int>()
    val selectedPostId: LiveData<Int> = _id

    /**
     * Set the quantity of cupcakes for this order.
     *
     * @param numberCupcakes to order
     */
    fun setQuantity(numberCupcakes: Int) {
        _id.value = numberCupcakes
    }

    fun resetSelection() {
        _id.value = 0
    }



}