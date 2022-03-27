package com.example.rickandmorty.retrofit

import com.example.rickandmorty.sections.character.Character
import com.example.rickandmorty.sections.character.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServices {

    @GET("api/character")
    fun getAllCharacters(@Query ("page") page: Int): Call<Characters>

    @GET("api/character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<Character>

//    @GET("api/?key=22773337-ca3caa766daa279b05a6882d5")
//    fun getPicturesList(@Query("q") search: String, @Query("page") page: Int): Call<>
}