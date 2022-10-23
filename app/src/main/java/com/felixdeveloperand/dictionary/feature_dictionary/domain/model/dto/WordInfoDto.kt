package com.felixdeveloperand.dictionary.feature_dictionary.domain.model.dto

import com.felixdeveloperand.dictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin:String,
    val phonetic: String,
    val phoneticDtos: List<PhoneticDto>,
    val word: String
){
    fun toWordInfo():WordInfo{
        return WordInfo(
            meanings = meanings.map { it.toMeaning() },
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }
}