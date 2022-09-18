package com.openrun.core_data.di
import com.openrun.core_data.repository.ReqResRepository
import com.openrun.core_data.repository.ReqResRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsReqResRepository(
        reqResRepositoryImpl: ReqResRepositoryImpl
    ): ReqResRepository
}