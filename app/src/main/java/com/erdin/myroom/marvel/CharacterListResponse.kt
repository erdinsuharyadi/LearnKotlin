package com.erdin.myroom.marvel

import com.google.gson.annotations.SerializedName

data class CharacterListResponse(val data: DataRes?) : BaseApiResponse() {

    data class DataRes(val results: List<Character>?)

    data class Character(val id: String?,
                         val name: String?,
                         val description: String?,
                         @SerializedName("thumbnail")
                         val imageCharacter: ImageUrl?,
                         val urls: List<UrlDetail>?)

    data class ImageUrl(val path: String?, val extension: String?)

    data class UrlDetail(val type: String?, val url: String?)


}