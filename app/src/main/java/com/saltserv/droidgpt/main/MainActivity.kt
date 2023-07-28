package com.saltserv.droidgpt.main

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.saltserv.droidgpt.ui.gpt.MyGPT
import com.saltserv.droidgpt.ui.theme.DroidGPTTheme
import java.util.Locale

class MainActivity : ComponentActivity(), OnInitListener {
    private lateinit var ttsObject: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ttsObject = TextToSpeech(this, this)

        setContent {
            DroidGPTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyGPT(ttsObject)
                }
            }
        }
    }

    override fun onInit(status: Int) {
        val result = ttsObject.setLanguage(Locale.ENGLISH)
        if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.d("TTS", "onInit: Error langugae not supported")
        }
    }
}