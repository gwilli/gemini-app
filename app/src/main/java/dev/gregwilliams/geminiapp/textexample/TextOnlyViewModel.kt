package dev.gregwilliams.geminiapp.textexample

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TextOnlyViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _inputMessage = mutableStateOf("")
    var inputMessage: String
        get() = _inputMessage.value
        set(value) { _inputMessage.value = value }
    private val _isLoading = mutableStateOf(false)
    val isLoading: Boolean
        get() = _isLoading.value
    private val _response = mutableStateOf("")
    val response: String
        get() = _response.value


    private suspend fun queryAI(query: String): String {
        // TODO call AI with query
        delay(2000L)
        return "I'm sorry, I don't know anything about \"$query\"."
    }

    fun sendQuery() {
        viewModelScope.launch {
            _isLoading.value = true
            _response.value = queryAI(_inputMessage.value)
            _isLoading.value = false
        }
    }
}
