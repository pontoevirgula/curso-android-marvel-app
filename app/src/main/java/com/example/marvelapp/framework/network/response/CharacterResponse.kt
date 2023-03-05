package com.example.marvelapp.framework.network.response

import com.example.core.domain.model.CharacterItem

data class CharacterResponse(
    val id: Int,
    val name: String,
    val thumbnail: ThumbnailResponse
){
    fun toCharacterModel() : CharacterItem =
        CharacterItem(
            name = this.name,
            imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}"
        )
}

