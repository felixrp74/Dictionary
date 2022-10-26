package com.felixdeveloperand.dictionary.feature_dictionary.domain.presentation

import com.felixdeveloperand.dictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading : Boolean = false
)
