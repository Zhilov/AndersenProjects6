package com.example.diexample

import android.app.Application
import com.example.diexample.DI.AppComponent
import com.example.diexample.DI.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}