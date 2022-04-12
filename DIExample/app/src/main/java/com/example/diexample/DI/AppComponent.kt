package com.example.diexample.DI

import com.example.diexample.MainActivity
import dagger.Component

@Component(modules = [Module::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}