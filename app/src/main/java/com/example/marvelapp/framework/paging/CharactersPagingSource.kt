package com.example.marvelapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.repository.CharactersRemoteDataSource
import com.example.core.domain.model.CharacterItem
import com.example.marvelapp.framework.network.response.DataWrapperResponse

class CharactersPagingSource(
    private val queryStartWith: String,
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
) : PagingSource<Int, CharacterItem>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        return try {
            val offset = params.key ?: 0

            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            if (queryStartWith.isNotEmpty()) {
                queries["nameStartWith"] = queryStartWith
            }
            val response = remoteDataSource.fetchCharacters(queries)
            val responseOffset = response.data.offset
            val totalCharacters = response.data.total
            LoadResult.Page(
                data = response.data.results.map { characterResponse ->
                    characterResponse.toCharacterModel()
                },
                prevKey = null,
                nextKey =  if (responseOffset < totalCharacters)
                    responseOffset + LIMIT
                else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object{
        private const val LIMIT = 20
    }
}