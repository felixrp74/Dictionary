package com.felixdeveloperand.dictionary.feature_dictionary.data.remote

import com.felixdeveloperand.dictionary.feature_dictionary.domain.model.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    // https://api.dictionaryapi.dev/api/v2/entries/en/morning
    // https://youtu.be/Mr8YKDh3li4?t=1349
    @GET("api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word:String
    ): List<WordInfoDto>

}