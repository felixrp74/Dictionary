package com.felixdeveloperand.dictionary.feature_dictionary.domain.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felixdeveloperand.dictionary.core.util.Resource
import com.felixdeveloperand.dictionary.feature_dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearchQuery(query:String){
        _searchQuery.value = query
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfo(query)
                .onEach {
                    when(it){
                        is Resource.Success ->{
                            _state.value = state.value.copy(
                                wordInfoItems = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error ->{
                            _state.value = state.value.copy(
                                wordInfoItems = it.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackbar(
                                it.message ?: "Unknown error"
                            ))
                        }
                        is Resource.Loading ->{
                            _state.value = state.value.copy(
                                wordInfoItems = it.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent{
        data class ShowSnackbar(val message:String): UIEvent()
    }

}