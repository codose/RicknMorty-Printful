package com.ogie.printfultest.network.repository

import com.ogie.printfultest.model.RickMorty
import com.ogie.printfultest.model.RickMortyResponse
import com.ogie.printfultest.network.api.ApiService
import com.ogie.printfultest.utils.ApiResponse

interface INetworkRepository {
    val api: ApiService?
    suspend fun fetchList() : ApiResponse<RickMortyResponse>
    suspend fun fetchItem(id : Int) : ApiResponse<RickMorty>
}