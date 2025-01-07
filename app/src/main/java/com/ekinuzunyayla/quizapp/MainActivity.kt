package com.ekinuzunyayla.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ekinuzunyayla.quizapp.ui.screen.ChristmasJokeScreen
import com.ekinuzunyayla.quizapp.ui.screen.JokeScreen
import com.ekinuzunyayla.quizapp.ui.theme.QuizAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizAppTheme {
                var currentScreen by remember { mutableStateOf(Screen.Programming) }
                
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Code, "Programming") },
                                label = { Text("Programming") },
                                selected = currentScreen == Screen.Programming,
                                onClick = { currentScreen = Screen.Programming }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Celebration, "Christmas") },
                                label = { Text("Christmas") },
                                selected = currentScreen == Screen.Christmas,
                                onClick = { currentScreen = Screen.Christmas }
                            )
                        }
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        when (currentScreen) {
                            Screen.Programming -> JokeScreen()
                            Screen.Christmas -> ChristmasJokeScreen()
                        }
                    }
                }
            }
        }
    }
}

enum class Screen {
    Programming,
    Christmas
}


