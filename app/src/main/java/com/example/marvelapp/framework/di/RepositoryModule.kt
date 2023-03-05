package com.example.marvelapp.framework.di

import com.example.core.data.repository.CharacterRepository
import com.example.core.data.repository.CharactersRemoteDataSource
import com.example.marvelapp.framework.CharacterRepositoryImpl
import com.example.marvelapp.framework.network.remote.RetrofitRemoteDataSource
import com.example.marvelapp.framework.network.response.DataWrapperResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsCharacters(characterRepositoryImpl: CharacterRepositoryImpl) : CharacterRepository

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitRemoteDataSource
    ) : CharactersRemoteDataSource<DataWrapperResponse>
}