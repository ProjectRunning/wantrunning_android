package com.openrun.core_network.service

import com.openrun.core_model.UserList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WantRunningService {

    @GET("api/users")
    suspend fun fetchUserList(): Response<UserList>
}