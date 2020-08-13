package com.example.databindingtest


import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CountLiveData : ViewModel() {
    val counterLiveBg: MutableLiveData<String> = MutableLiveData<String>().apply { value = "empty" }
    val counterObservable : ObservableField<String> = ObservableField<String>("empty")

    private var counter: Int = 0

    private val timer = Timer()
    lateinit var lifecycleOwner: LifecycleOwner

    fun startTimer() {
        counterLiveBg.observeForever({
            Log.d("TAG", "Hi counterLiveBg observeForever tells me the new value is ${counterLiveBg.value}")
        })
        counterLiveBg.observe(lifecycleOwner, androidx.lifecycle.Observer {
            Log.d("TAG", "Hi counterLiveBg observe with lifecycleOwner tells me the new value is ${counterLiveBg.value}")
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