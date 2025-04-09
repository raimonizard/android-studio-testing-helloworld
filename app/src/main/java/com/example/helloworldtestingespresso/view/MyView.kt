package com.example.helloworldtestingespresso.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.helloworldtestingespresso.viewmodel.HelloViewModel

@Composable
fun MyView(myViewModel: HelloViewModel, modifier: Modifier = Modifier) {
    val textValue by myViewModel.textValue.observeAsState("")
    val checkBoxMulti by myViewModel.checkBoxMulti.observeAsState(false)
    val counterValue by myViewModel.counterValue.observeAsState(0)

    LazyColumn(modifier = modifier
        .padding(20.dp)
        .fillMaxSize(1.00f),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                item{
                    Text(
                        text = "Hello $textValue!",
                        modifier = Modifier
                            .testTag("initialText_id")
                    )
                }
                item{
                    Text(
                        text = "$counterValue",
                        modifier = Modifier
                            .testTag("counterText_id"),
                        color = Color.Green
                    )
                }
                item{
                    Checkbox(
                        checked = checkBoxMulti,
                        onCheckedChange = {myViewModel.toggleCheckBoxMulti()}, // Invertim el valor de la variable al interactuar amb el Checkbox
                        modifier = Modifier
                            .fillMaxWidth(0.20f) // Fem que el component Switch sigui responsive ocupi el 40% de l'amplada del component superior
                            .testTag("checkBoxMulti_id"),
                        enabled = true,
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.LightGray,
                            checkmarkColor = Color.Black)
                    )
                }
            }
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
                item{
                    Button(onClick = { myViewModel.clickIncrement() },
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