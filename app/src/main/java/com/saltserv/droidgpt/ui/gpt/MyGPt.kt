package com.saltserv.droidgpt.ui.gpt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saltserv.droidgpt.main.MainViewModel

@Preview
@Composable
private fun MyGPTPreview() {
    MyGPT()
}

@Composable
fun MyGPT(
    viewModel: MainViewModel = hiltViewModel()
) {
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GPTField(viewModel.gptQuery)

            Spacer(modifier = Modifier.height(16.dp))

            GetGPTButton {
                viewModel.getGPTResponse()
            }

            GPTResponse(viewModel.gptResponse.value)
        }
    }
}
