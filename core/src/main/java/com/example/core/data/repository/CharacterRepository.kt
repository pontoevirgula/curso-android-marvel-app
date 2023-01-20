package com.example.core.data.repository

import androidx.paging.PagingSource
import com.example.core.domain.model.CharacterItem

interface CharacterRepository {

    fun getCharacters(query: String) : PagingSource<Int, CharacterItem>
}