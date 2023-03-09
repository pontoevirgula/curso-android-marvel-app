package com.example.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.repository.CharacterRepository
import com.example.core.domain.model.CharacterItem
import com.example.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) : PagingUseCase<GetCharacterParams, CharacterItem>(){

    override fun createFlowObservable(params: GetCharacterParams): Flow<PagingData<CharacterItem>> =
        Pager(config = params.pagingConfig){
            repository.getCharacters(params.query)
        }.flow
}

data class GetCharacterParams(val query : String, val pagingConfig: PagingConfig)
