package com.andersenlab.mvvm

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmexample.R

class SecondActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var loadingView: View
    private lateinit var textView: TextView

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingView = findViewById(R.id.loading)
        textView = findViewById(R.id.text)

        findViewById<View>(R.id.button).setOnClickListener {
            viewModel.upload()
        }

        viewModel.loadingState.observe(this){
            it?.let {
                loadingView.visibility = if(it) View.VISIBLE else View.INVISIBLE
            }
        }

        viewModel.progressState.observe(this){
            it?.let {
                textView.text = it
            }
        }

    }

}