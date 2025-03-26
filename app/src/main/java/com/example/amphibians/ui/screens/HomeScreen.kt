package com.example.amphibians.ui.screens

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.amphibians.R


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
        topBar = {TopAppBarAmphibians()},
        modifier = modifier
    ) {
        innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(amphibians ) {
                    amphibian -> AmphibianCard(
                        amphibian = amphibian
                    )
                }
            }

    }
}

@Composable
fun AmphibianCard(
    amphibian: AmphibiansData
){
    Card (
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 5.dp,
            bottomEnd = 5.dp
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = amphibian.name + " (${amphibian.type})",
                modifier = Modifier
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp

            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .build(),
                contentDescription = amphibian.name,
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                amphibian.description,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Justify
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarAmphibians(){
    TopAppBar(
        title = {Text(text = "Amphibians")}
    )
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
