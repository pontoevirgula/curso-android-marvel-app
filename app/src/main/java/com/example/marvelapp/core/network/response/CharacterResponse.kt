package com.example.marvelapp.core.network.response

data class CharacterResponse(
    val id: Int,
    val name: String,
    val thumbnail: ThumbnailResponse
)
