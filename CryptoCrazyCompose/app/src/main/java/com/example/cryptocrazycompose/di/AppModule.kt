package com.example.cryptocrazycompose.di

import com.example.cryptocrazycompose.data.datasource.CryptoDataSource
import com.example.cryptocrazycompose.data.repository.CryptoRepository
import com.example.cryptocrazycompose.retrofit.ApiUtils
import com.example.cryptocrazycompose.retrofit.CryptoAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getCryptoDao():CryptoAPI{
        return ApiUtils.getCryptoDao()
    }

    @Provides
    @Singleton
    fun getCryptoRepository(cdso:CryptoDataSource):CryptoRepository{
        return CryptoRepository(cdso)
    }

    @Provides
    @Singleton
    fun getCryptoDataSource(cdao:CryptoAPI):CryptoDataSource{
        return CryptoDataSource(cdao)
    }
}