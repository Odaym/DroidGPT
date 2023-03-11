package com.saltserv.droidgpt.ui.gpt

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun GPTFieldPreview() {
    GPTField()
}

@Composable
fun GPTField(
    gptQuery: MutableState<String>? = null
) {
    val text = remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.wrapContentSize(),
        value = text.value,
        onValueChange = {
            text.value = it
            gptQuery?.value = it
        }
    )
}
