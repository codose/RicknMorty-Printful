package com.ogie.printfultest.network.repository

import com.ogie.printfultest.model.RickMorty
import com.ogie.printfultest.model.RickMortyResponse
import com.ogie.printfultest.network.api.ApiService
import com.ogie.printfultest.network.api.RetrofitClient
import com.ogie.printfultest.utils.ApiResponse

class NetworkRepositoryImpl : INetworkRepository {
    override val api: ApiService = RetrofitClient.apiService()

    override suspend fun fetchList() : ApiResponse<RickMortyResponse>{
        return try {
            val data = api.fetchList().await()
            ApiResponse.Success(data)
        }catch (t : Throwable){
            ApiResponse.Failure(t.message!!)
        }
    }

    override suspend fun fetchItem(id : Int) : ApiResponse<RickMorty>{
        return try {
            val data = api.fetchItem(id).await()
            ApiResponse.Success(data)
        }catch (t : Throwable){
            ApiResponse.Failure(t.message!!)
        }
    }
}