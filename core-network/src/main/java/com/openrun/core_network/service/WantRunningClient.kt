package com.openrun.core_network.service

import com.openrun.core_model.UserList
import retrofit2.Response
import javax.inject.Inject

class WantRunningClient @Inject constructor(
    private val wantRunningService: WantRunningService
) {
    suspend fun fetchUserList() : Response<UserList> = wantRunningService.fetchUserList()
}
