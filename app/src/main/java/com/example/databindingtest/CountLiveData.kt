package com.example.databindingtest


import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CountLiveData : ViewModel() {
    val counterLiveBg: MutableLiveData<String> = MutableLiveData<String>().apply { value = "empty" }
    val counterObservable : ObservableField<String> = ObservableField<String>("empty")

    private var counter: Int = 0

    private val timer = Timer()

    fun startTimer() {
        counterLiveBg.observeForever({
            Log.d("TAG", "Hi counterLiveBg tells me the new value is ${counterLiveBg.value}")
        })

        timer.scheduleAtFixedRate(object : TimerTask()  {
            override fun run() {
                counter++
                counterLiveBg.postValue(counter.toString())
                counterObservable.set(counter.toString())
            }
        }, 0, 1000)
    }
}