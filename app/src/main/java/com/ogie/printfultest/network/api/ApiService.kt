package com.ogie.printfultest.network.api

import com.ogie.printfultest.model.RickMorty
import com.ogie.printfultest.model.RickMortyResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

/*
    Created By : Osemwingie Oshodin
    Github : https://github.com/codose
 */

interface ApiService {

    @GET("character")
    fun fetchList() : Deferred<RickMortyResponse>

    @GET("character/{id}")
    fun fetchItem(@Path("id") id : Int) : Deferred<RickMorty>

}