package com.openrun.core_data.repository

import androidx.annotation.WorkerThread
import com.openrun.core_model.UserList

interface ReqResRepository {

    @WorkerThread
    suspend fun fetchUserList(
    ): UserList?
}