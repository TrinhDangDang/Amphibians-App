package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.model.AmphibiansData
import com.example.amphibians.ui.theme.AmphibiansTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale


@Composable
fun HomeScreen() {
    val viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
    val uiState: AmphibiansUiState = viewModel.amphibiansUiState

    when(uiState){
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Success -> AmphibianScreen(amphibians = uiState.amphibians)
        is AmphibiansUiState.Error -> ErrorScreen()
    }
//    Scaffold { innerPadding ->
//        Surface (modifier = Modifier.padding(innerPadding)){
//            when (uiState) {
//                AmphibiansUiState.Loading -> Text("Loading...")
//                is AmphibiansUiState.Success -> {
//                    val amphibians = uiState.amphibians
//                    if (amphibians.isNotEmpty()) {
//                        LazyColumn {
//                            items(amphibians) { amphibian ->
//                                AmphibianCard(
//                                    amphibian = amphibian
//                                )
//
//                            }
//                        }
//                    } else {
//                        Text("No amphibians found.")
//                    }
//                } // // Assuming Success has data.
//                AmphibiansUiState.Error -> Text("Error loading data.")
//            }
//        }
//    }
}

@Composable
fun LoadingScreen(){
    Text(
        text = "success"
    )
}

@Composable
fun ErrorScreen(){
    Text(
        text = "success"
    )
}

@Composable
fun AmphibianScreen(
    amphibians: List<AmphibiansData>,
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier
    ) {
        innerPadding -> Surface(modifier = Modifier.padding(innerPadding)){
            LazyColumn {
                items(amphibians ) {
                    amphibian -> AmphibianCard(
                        amphibian = amphibian
                    )
                }
            }

    }
    }
}

@Composable
fun AmphibianCard(
    amphibian: AmphibiansData
){
    Card {
        Column {
            Text(
                text = amphibian.name + " (${amphibian.type})"
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .build(),
                contentDescription = amphibian.name,
                contentScale = ContentScale.Crop
            )
            Text(
                amphibian.description
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val sampleData = listOf(
        AmphibiansData(
            name = "Roraima Bush Toad",
            type = "Toad",
            description = "A small toad from South America.",
            imgSrc = "https://example.com/image.png"
        )
    )

    val fakeUiState = AmphibiansUiState.Success(amphibians = sampleData)

    // Fake version of HomeScreen content, using mock uiState
    AmphibiansTheme {
        Scaffold {
            Surface(modifier = Modifier.padding(it)) {
                when(fakeUiState){
                    is AmphibiansUiState.Loading -> LoadingScreen()
                    is AmphibiansUiState.Success -> AmphibianScreen(fakeUiState.amphibians)
                    is AmphibiansUiState.Error -> ErrorScreen()
                }
            }
        }
    }
}
