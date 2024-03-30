package dev.gregwilliams.geminiapp.textexample

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gregwilliams.geminiapp.util.WhileUiSubscribed
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TextOnlyViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _inputMessage = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)
    private val _response = MutableStateFlow("")
    private val _asyncQuery = flow<String> {
        if (_isLoading.value) {
            queryAI(_inputMessage.value)
        }
    }.catch { emit(it.message ?: "Unknown error occurred") }

    val uiState: StateFlow<TextOnlyUiState> = combine(
        _isLoading, _inputMessage, _response, _asyncQuery
    ) { isLoading, inputMessage, response, asyncQuery ->
        TextOnlyUiState(inputMessage, response, isLoading)
    }
        .stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = TextOnlyUiState(
                inputMessage = _inputMessage.value,
                response = _response.value,
                isLoading = _isLoading.value
            )
        )

    private suspend fun queryAI(query: String): String {
        // TODO call AI with query
        delay(2000L)
        return "I'm sorry, I don't know anything about $query."
    }

    fun sendQuery() {
        viewModelScope.launch {
            _isLoading.value = true
            _response.value = queryAI(_inputMessage.value)
            _isLoading.value = false
        }
    }
}

const val TEXT_ONLY_SAVED_STATE_KEY = "text_only_state_key"

data class TextOnlyUiState(
    var inputMessage: String = "",
    val response: String = "",
    val isLoading: Boolean = false
)