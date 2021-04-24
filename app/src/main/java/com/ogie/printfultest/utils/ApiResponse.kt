package com.ogie.printfultest.utils

/*
    Created By : Osemwingie Oshodin
    Date : 23-4-2021
    Github : https://github.com/codose
 */

sealed class ApiResponse<out T> {
    class Loading<out T> : ApiResponse<T>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Failure<out T>(val message: String, val code : Int = 0) : ApiResponse<T>()
}
