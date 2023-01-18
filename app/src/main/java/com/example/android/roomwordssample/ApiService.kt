package com.example.android.roomwordssample

import retrofit2.http.*

interface ApiService {

    @GET("random")
    suspend fun getCitation(): Citation

}