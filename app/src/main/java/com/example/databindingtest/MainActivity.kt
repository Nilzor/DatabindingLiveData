package com.example.databindingtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databindingtest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: CountLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, VmFactory).get(CountLiveData::class.java)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        viewModel.startTimer()
    }
}