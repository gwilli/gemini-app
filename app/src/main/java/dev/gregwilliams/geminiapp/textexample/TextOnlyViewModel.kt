package dev.gregwilliams.geminiapp.textexample

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gregwilliams.geminiapp.util.WhileUiSubscribed
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TextOnlyViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val uiState = savedStateHandle.getStateFlow(TEXT_ONLY_SAVED_STATE_KEY, TextOnlyUiState())

    private suspend fun queryAI(query: String): String {
        // TODO call AI with query
        delay(2000L)
        return "I'm sorry, I don't know."
    }

    fun updateQuery(input: String) {
        uiState.value.inputMessage = input
    }

    fun sendQuery() = viewModelScope.launch {
        savedStateHandle[TEXT_ONLY_SAVED_STATE_KEY] = flow<String> { queryAI(uiState.value.inputMessage) }
            .catch {
                emit(it.message ?: "An unknown error occurred")
            }
            .map {
                TextOnlyUiState(response = it)
            }
            .stateIn(
                scope = viewModelScope,
                started = WhileUiSubscribed,
                initialValue = TextOnlyUiState(isLoading = true)
            )
    }
}

const val TEXT_ONLY_SAVED_STATE_KEY = "text_only_state_key"

data class TextOnlyUiState(
    var inputMessage: String = "",
    val response: String = "",
    val isLoading: Boolean = false
)