package com.ogie.printfultest.model


import com.google.gson.annotations.SerializedName

data class RickMortyResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val rickMorties: List<RickMorty>
)