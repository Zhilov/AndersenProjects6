package com.example.diexample.DI

import dagger.Module
import dagger.Provides

@Module
class Module {

    @Provides
    fun provide(): String{
        return "Dmitriy"
    }
}