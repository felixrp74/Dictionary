package com.felixdeveloperand.dictionary.feature_dictionary.domain.model.dto

import com.felixdeveloperand.dictionary.feature_dictionary.data.local.entity.WordInfoEntity
import com.felixdeveloperand.dictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin:String,
    val phonetic: String,
    val phoneticDtos: List<PhoneticDto>,
    val word: String
){
    fun toWordInfoEntity():WordInfoEntity{
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }
}