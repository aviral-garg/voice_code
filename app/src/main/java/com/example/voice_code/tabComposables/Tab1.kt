package com.example.voice_code.tabComposables

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.material.*
import androidx.ui.res.colorResource
import androidx.ui.res.stringResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.voice_code.DefaultPreview
import com.example.voice_code.R
import com.example.voice_code.dataProviders.DataProvider
import com.example.voice_code.dataProviders.Question
import com.example.voice_code.dataProviders.Tabs


@Preview
@Composable
fun DefaultPreview_Tab1() {
    DefaultPreview(Tabs.Tab1)
}

// TODO: Should questions be sent as a param or should it be private?
// TODO: Change the name `Tab1`
@Composable
fun Tab1(
        questions: MutableList<Question> = DataProvider.questionsList
) {

    val difficultyStrings: List<String> = listOf(stringResource(R.string.Unknown), "Easy", "Medium", "Hard")
    val difficultyColors: List<Color> = listOf(
            colorResource(R.color.Unknown),
            colorResource(R.color.Easy),
            colorResource(R.color.Medium),
            colorResource(R.color.Hard)
    )

    VerticalScroller {
        DataTable(
                borderWidth = 0.dp,
                dataRowHeight = 50.dp,
                columns = 4,
////              Reason: Using `VerticalScroller` instead of `pagination`
//                pagination = DefaultDataTablePagination(
//                        initialRowsPerPage = 10,
//                        availableRowsPerPage = questions.chunked(10).map { it.size }
//                ),
                sorting = DefaultDataTableSorting(
                        //TODO: Sort by id and Status
                        sortableColumns = setOf(3),
                        onSortRequest = { _, isAscending ->
                            if (isAscending) {
                                questions.sortBy { it.difficulty }
                            } else {
                                questions.sortByDescending { it.difficulty }
                            }
                        }
                )
        ) {
            headerRow { index ->
                when (index) {
                    0 -> Text(text = "Id")
                    1 -> Text(text = "Status")
                    2 -> Text(text = "Question")
                    3 -> Text(text = "Difficulty")
                }

            }

            for (question in questions) {
                dataRow { index: Int ->
                    when (index) {
                        0 -> Text(text = "${question.id}")
                        1 -> Checkbox(
                                checked = question.isSolved,
                                onCheckedChange = null
                        )
                        2 -> Container(
                                // TODO: add ... if overflow
                                // TODO: Hold to show full name
                                width = 100.dp,
                                alignment = Alignment.CenterLeft
                        ) {
                            Text(
                                    maxLines = 1,
                                    text = question.title
                            )
                        }
                        3 -> Button(
                                text = difficultyStrings[question.difficulty],
                                style = ButtonStyle(
                                        // TODO: same width for all buttons
                                        shape = (MaterialTheme.shapes()).button,
                                        backgroundColor = difficultyColors[question.difficulty],
                                        contentColor = (MaterialTheme.colors()).primary
                                )
                        )
                    }
                }
            }

        }
    }
}