package dev.gregwilliams.geminiapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.gregwilliams.geminiapp.R
import dev.gregwilliams.geminiapp.textexample.TextOnlyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextOnlyScreen(
    modifier: Modifier = Modifier,
    viewModel: TextOnlyViewModel
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(R.string.textonly_title)) }
            )
        }
    ) { paddingValues ->

        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                value = uiState.inputMessage,
                onValueChange = { input -> viewModel.updateQuery(input) },
                minLines = 2
            )
            Button(
                modifier = Modifier.width(170.dp),
                onClick = { viewModel.sendQuery() }
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(text = stringResource(id = R.string.textonly_button_text))
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = uiState.response
            )
        }
    }
}

