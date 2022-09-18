package com.openrun.core_data.repository

import androidx.annotation.WorkerThread
import com.openrun.core_model.UserList
import javax.inject.Inject
import com.openrun.core_network.service.WantRunningClient

class ReqResRepositoryImpl @Inject constructor(
    private val wantRunningClient: WantRunningClient,
) : ReqResRepository {

    @WorkerThread
    override suspend fun fetchUserList(): UserList? {
        return wantRunningClient.fetchUserList().body()
    }
}