package com.example.marvelapp.framework

import androidx.paging.PagingSource
import com.example.core.data.repository.CharacterRepository
import com.example.core.data.repository.CharactersRemoteDataSource
import com.example.core.domain.model.CharacterItem
import com.example.marvelapp.framework.network.response.DataWrapperResponse
import com.example.marvelapp.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
) : CharacterRepository {

    override fun getCharacters(query: String): PagingSource<Int, CharacterItem> =
        CharactersPagingSource(query, charactersRemoteDataSource)


}