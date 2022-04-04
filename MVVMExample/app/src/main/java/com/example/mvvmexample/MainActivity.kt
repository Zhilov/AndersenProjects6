package com.example.mvvmexample

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.andersenlab.mvvm.SecondActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter("android.intent.action.REBOOT")

        findViewById<View>(R.id.button).setOnClickListener {
            startActivity(Intent(
                this, SecondActivity::class.java
            ))
        }
    }

}