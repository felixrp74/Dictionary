package com.felixdeveloperand.dictionary.feature_dictionary.domain.model.dto

import com.felixdeveloperand.dictionary.feature_dictionary.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
){
    fun toMeaning():Meaning{
        return Meaning(
            definitions = definitions.map { it.toDefinition() } ,
            partOfSpeech = partOfSpeech
        )
    }

}