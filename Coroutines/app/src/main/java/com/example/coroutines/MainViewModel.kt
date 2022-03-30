package com.example.coroutines

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel : ViewModel(){

    val state = MutableLiveData<String>()

    var job = Job()

    var errorHandler = CoroutineExceptionHandler{context, throwable ->
        throwable.printStackTrace()
    }

    fun startLoad(){
        job.cancel()

        CoroutineScope(job + errorHandler).launch {
            state.postValue("Start Loading")

            val result = withContext(Dispatchers.IO){
                longNetworkOperation()
            }

            state.postValue("Loading finish $result")
        }
    }


    private suspend fun longNetworkOperation(){
        if (Looper.myLooper() == Looper.getMainLooper()){
            error("")
        }

        for (i in 1..5){

        }
    }
}