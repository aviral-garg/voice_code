package com.example.voice_code.tabComposables


import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.Text
import androidx.ui.graphics.Color
import androidx.ui.material.Button
import androidx.ui.material.ButtonStyle
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.example.voice_code.DefaultPreview
import com.example.voice_code.dataProviders.Tabs

@Preview
@Composable
fun DefaultPreview_Tab4() {
    DefaultPreview(Tabs.Tab4)
}

@Model
class ListeningState(var isListening: Boolean = false)

@Composable
fun Tab4(
    state: ListeningState = ListeningState(true)
) {
    Button(
        text = "Speech",
        onClick = { state.isListening = !state.isListening },
        style = ButtonStyle(
            backgroundColor = if (state.isListening) Color.Green else Color.White,
            contentColor = Color.Black,
            shape = (MaterialTheme.shapes()).button
        )

    )
}