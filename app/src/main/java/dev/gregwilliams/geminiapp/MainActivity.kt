package dev.gregwilliams.geminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dev.gregwilliams.geminiapp.textexample.TextOnlyViewModel
import dev.gregwilliams.geminiapp.ui.theme.GeminiApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textOnlyViewModel:TextOnlyViewModel by viewModels()

        setContent {
            GeminiApplicationTheme {
                MainNavGraph(textOnlyViewModel = textOnlyViewModel)
            }
        }
    }
}
