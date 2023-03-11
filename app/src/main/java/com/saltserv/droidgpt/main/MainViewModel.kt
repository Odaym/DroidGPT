package com.saltserv.droidgpt.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.*
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val gptResponse = mutableStateOf("")
    val gptQuery = mutableStateOf("")

    @OptIn(BetaOpenAI::class)
    fun getGPTResponse() {
        viewModelScope.launch {
            val openAI = OpenAI(CHAT_GPT_API_KEY)

            try {
                val chatCompletionRequest = ChatCompletionRequest(
                    model = ModelId("gpt-3.5-turbo"),
                    messages = listOf(
                        ChatMessage(
                            role = ChatRole.User,
                            content = gptQuery.value
                        )
                    )
                )

                val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)

                val response = completion.choices.first().message?.content

                gptResponse.value = response ?: ""
            } catch (e: Exception) {
                gptResponse.value = "ERROR: ${e.message ?: ""}"
            }
        }
    }

    companion object {
        const val CHAT_GPT_API_KEY = "<YOUR_API_KEY>"
    }
}