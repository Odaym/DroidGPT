package com.saltserv.droidgpt.ui.gpt

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GPTResponse(
    response: String? = null
) {
    Text(
        modifier = Modifier.padding(10.dp),
        text = response ?: "No GPT Response"
    )
}