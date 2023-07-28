package com.saltserv.droidgpt.main

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.util.Range
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.*
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.saltserv.droidgpt.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val gptQuery = mutableStateOf("")

    val word = mutableStateOf("")

    @OptIn(BetaOpenAI::class)
    fun getGPTResponse(textToSpeech: TextToSpeech) {
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

                say(textToSpeech, response)
            } catch (e: Exception) {
                Log.d(TAG, "getGPTResponse: ERROR: ${e.message ?: ""}")
            }
        }
    }

    private fun say(textToSpeech: TextToSpeech, response: String?) {
        textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onRangeStart(utteranceId: String?, start: Int, end: Int, frame: Int) {
                super.onRangeStart(utteranceId, start, end, frame)

                word.value = "${word.value} ${response?.substring(start, end) ?: ""}"
            }

            override fun onStart(p0: String?) {
                word.value = ""
            }

            override fun onDone(p0: String?) {}

            override fun onError(p0: String?) {}
        })

        textToSpeech.speak(
            response,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "utterance_id"
        )
    }

    companion object {
        const val CHAT_GPT_API_KEY = "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"
    }
}