package com.coderednt.mynia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DeviceInfo.initialize(applicationContext)

        val deviceInfo = DeviceInfo.getInfo()

        Log.d("MyNIA", "Device Info: $deviceInfo")

        repeat(3) {
            GlobalScope.launch {
                Log.d("MyNIA", "Thread processada ${Thread.currentThread()}")
            }
        }
    }
}