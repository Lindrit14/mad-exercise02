package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import androidx.compose.material3.Card
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        BodyContent(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text("MovieAppMAD24") }
    )
}

@Composable
fun BottomBar() {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = {println("Hello World from Home") }


        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Watchlist") },
            label = { Text("Watchlist") },
            selected = false,
            onClick = { println("Hello World from Home") }
        )
    }
}

@Composable
fun BodyContent(paddingValues: PaddingValues) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)) {
        // Your body content here, for example, a MovieImageCard()
        MovieImageCard()
    }
}
@Composable
fun MovieImageCard() {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize(), // This will animate the size change of the card
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.movie_image),
                contentDescription = "Movie Image",
                modifier = Modifier.fillMaxWidth() // Change to fillMaxWidth to maintain aspect ratio
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "300",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(1f) // This will make the text take up max horizontal space
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropDown,
                        contentDescription = "Toggle Details",
                        modifier = Modifier
                            .graphicsLayer {
                                // This rotation will handle the arrow turning
                                rotationZ = if (expanded) 180f else 0f
                }
                    )
            }}
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Director: Zack Snyder",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Released: 2006",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Plot: King Leonidas of Sparta and a force of 300 men fight the Persians at Thermopylae in 480 B.C.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppMAD24Theme {
        MainScreen()
    }
}
