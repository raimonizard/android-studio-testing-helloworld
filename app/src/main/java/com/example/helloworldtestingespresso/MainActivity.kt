package com.example.helloworldtestingespresso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.helloworldtestingespresso.ui.theme.HelloWorldTestingEspressoTheme
import com.example.helloworldtestingespresso.view.MyView
import com.example.helloworldtestingespresso.viewmodel.HelloViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val myViewModel by viewModels<HelloViewModel>()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldTestingEspressoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyView(
                        myViewModel = myViewModel,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}