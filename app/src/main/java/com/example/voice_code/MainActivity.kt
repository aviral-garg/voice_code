package com.example.voice_code

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.setContent
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Tab
import androidx.ui.material.TabRow
import androidx.ui.material.lightColorPalette
import androidx.ui.tooling.preview.Preview
import com.example.voice_code.dataProviders.Tabs
import com.example.voice_code.tabComposables.Tab1
import com.example.voice_code.tabComposables.Tab2
import com.example.voice_code.tabComposables.Tab3
import com.example.voice_code.tabComposables.Tab4


@Preview
@Composable
fun DefaultPreview(initialTab: Tabs = Tabs.Tab1) {
    App {
        QuestionListScreen(initialTab)
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                QuestionListScreen(Tabs.Tab1)
            }
        }
    }
}


@Composable
fun App(children: @Composable() () -> Unit) {
    MaterialTheme(
            colors = lightColorPalette(
                    // TODO('change palette')
                    background = Color(0xFFEED7C1),
                    primary = Color(0xFF432918),
                    primaryVariant = Color(0xFF432918),
                    secondary = Color(0xFFBD6940),
                    onBackground = Color(0xFFBD6940)
            )
    ) {
        MaterialTheme {
            DrawShape(shape = RectangleShape, color = Color(0xFFEED7C1))
            children()
        }
    }
}

@Composable
fun QuestionListScreen(initialTab: Tabs) {
    Container {
        var section by state { initialTab }
        val sectionTitles = Tabs.values().map { it.title }

        Column {
            Container(modifier = LayoutFlexible(1f)) {
                when (section) {
                    Tabs.Tab1 -> Tab1()
                    Tabs.Tab2 -> Tab2()
                    Tabs.Tab3 -> Tab3()
                    Tabs.Tab4 -> Tab4()
                }
            }
            TabRow(
                    items = sectionTitles,
                    // scrollable = true,
                    selectedIndex = section.ordinal
            ) { index, text ->
                Tab(text = text, selected = section.ordinal == index) {
                    section = Tabs.values()[index]
                }
            }
        }
    }
}
