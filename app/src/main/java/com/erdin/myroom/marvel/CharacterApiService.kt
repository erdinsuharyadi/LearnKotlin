package com.erdin.myroom.marvel

import retrofit2.Call
import retrofit2.http.GET

interface CharacterApiService {

    @GET("/v1/public/characters?ts=1&apikey=06a95016769f8c1b31ce530ca6e7e819&hash=1bb49860a650d93f0ba96493e8f5db9b")
    fun getAllCharacter(): Call<CharacterListResponse>

    @GET("/v1/public/characters?ts=1&apikey=06a95016769f8c1b31ce530ca6e7e819&hash=1bb49860a650d93f0ba96493e8f5db9b")
    suspend fun getCharacter(): CharacterListResponse
}