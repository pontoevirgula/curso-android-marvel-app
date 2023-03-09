package com.example.marvelapp.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.domain.model.CharacterItem
import com.example.core.usecase.GetCharacterParams
import com.example.core.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val useCase: GetCharacterUseCase
) : ViewModel() {

    fun charactersPagingData(query : String) : Flow<PagingData<CharacterItem>> =
        useCase(
            GetCharacterParams(query,getPageConfig())
        ).cachedIn(viewModelScope)

    private fun getPageConfig() = PagingConfig(
        pageSize = LIMIT_PAGE_SIZE
    )


    companion object{
        private const val LIMIT_PAGE_SIZE = 20
    }


}