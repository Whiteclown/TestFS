package com.example.testfs.di

import android.content.Context
import androidx.room.Room
import com.example.remote.data.api.BinApi
import com.example.remote.data.dao.BinDao
import com.example.remote.data.datasource.BinLocalDataSource
import com.example.remote.data.datasource.BinLocalDataSourceImpl
import com.example.remote.data.datasource.BinRemoteDataSource
import com.example.remote.data.datasource.BinRemoteDataSourceImpl
import com.example.remote.data.db.BinDataBase
import com.example.remote.data.repository.BinRepositoryImpl
import com.example.remote.domain.repository.BinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideBinApi(retrofit: Retrofit): BinApi =
        retrofit.create()

    @Provides
    @Singleton
    fun provideBinDataBase(@ApplicationContext context: Context): BinDataBase =
        Room.databaseBuilder(
            context,
            BinDataBase::class.java,
            "BinDataBase",
        ).build()

    @Provides
    @Singleton
    fun provideBinDao(dataBase: BinDataBase): BinDao =
        dataBase.binDao()

    @Provides
    @Singleton
    fun provideBinLocalDataStore(dao: BinDao): BinLocalDataSource =
        BinLocalDataSourceImpl(dao)

    @Provides
    @Singleton
    fun provideBinRemoteDataStore(api: BinApi): BinRemoteDataSource =
        BinRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideBinRepository(
        localDataSource: BinLocalDataSource,
        remoteDataSource: BinRemoteDataSource,
    ): BinRepository =
        BinRepositoryImpl(localDataSource, remoteDataSource)
}