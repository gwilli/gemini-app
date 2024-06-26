package dev.gregwilliams.geminiapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dev.gregwilliams.geminiapp.chatexample.ChatViewModel
import dev.gregwilliams.geminiapp.textexample.TextOnlyViewModel
import dev.gregwilliams.geminiapp.textimageexample.TextImageViewModel
import dev.gregwilliams.geminiapp.ui.theme.GeminiApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textOnlyViewModel:TextOnlyViewModel by viewModels()
        val textImageViewModel: TextImageViewModel by viewModels()
        val chatViewModel: ChatViewModel by viewModels()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            GeminiApplicationTheme {
                MainNavGraph(
                    textOnlyViewModel = textOnlyViewModel,
                    textImageViewModel = textImageViewModel,
                    chatViewModel = chatViewModel
                )
            }
        }
    }
}
