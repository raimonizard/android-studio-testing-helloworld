package com.example.helloworldtestingespresso.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.helloworldtestingespresso.viewmodel.HelloViewModel

@Composable
fun MyView(myViewModel: HelloViewModel, modifier: Modifier = Modifier) {
    val textValue by myViewModel.textValue.observeAsState("")
    val counter by myViewModel.numberOfClicks.observeAsState(0)

    LazyColumn(modifier = Modifier.padding(20.dp)) {
        item {
            Text(
                text = "Hello $textValue!",
                modifier = Modifier
                    .testTag("initialText_id")
            )
        }
        item{
            OutlinedTextField(
                value = textValue,
                onValueChange = { myViewModel.setTextValue(it) },
                label = { Text("Write here...") },
                modifier = Modifier
                    .testTag("outlinedTextField_id")
            )
        }
        item{
            LazyRow {
                item {
                    Button(onClick = { myViewModel.clickIncrement()
                                       myViewModel.setTextValue(textValue + counter)
                                    },
                        modifier = Modifier
                            .testTag("incrementButton_id")
                    ) {
                        Text("Counter")
                    }
                }
                item{
                    Button(onClick = { myViewModel.resetValues() },
                            modifier = Modifier
                                .testTag("resetButton_id")
                    ) {
                        Text("Reset")
                    }

                }
            }
        }
    }
}