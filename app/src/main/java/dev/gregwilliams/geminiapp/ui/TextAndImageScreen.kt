package dev.gregwilliams.geminiapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.gregwilliams.geminiapp.MainTopAppBar
import dev.gregwilliams.geminiapp.R

@Composable
fun TextAndImageScreen(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                openDrawer = openDrawer,
                titleResId = R.string.text_and_image_title
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {

        }
    }
}