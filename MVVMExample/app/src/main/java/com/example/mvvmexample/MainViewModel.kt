package com.andersenlab.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _progressState = MutableLiveData<String>()
    val progressState: LiveData<String> = _progressState

    fun upload() {
        viewModelScope.launch {
            _loadingState.postValue(true)

            for (i in 1..10) {
                _progressState.postValue("Uploading ${i * 20}%")

                delay(1000)

                Log.e("Log", "Upload $i")
            }

            _loadingState.postValue(false)
            _progressState.postValue("File uploaded")
        }
    }
}