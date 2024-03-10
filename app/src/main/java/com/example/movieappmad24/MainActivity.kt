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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import androidx.compose.material3.Card
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import coil.compose.rememberImagePainter

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


val sampleMovies = listOf(
    Movie(
        title = "Avatar",
        director = "James Cameron",
        year = 2009,
        plot = "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
        imageUrl = "https://www.sky.at/static/img/filmhighlights/sky_1601_avatar.jpg"
    ),
    Movie(
        title = "300",
        director = "Zack Snyder",
        year = 2006,
        plot = "King Leonidas of Sparta and a force of 300 men fight the Persians at Thermopylae in 480 B.C.",
        imageUrl = "https://pappaspost.com/wp-content/uploads/2020/11/300.jpg" // Replace with a valid URL
    ),
    Movie(
        title = "The Avengers",
        director = "Joss Whedon",
        year = 2012,
        plot = "Die mächtigsten Helden der Erde müssen zusammenkommen und lernen, gemeinsam als Team zu kämpfen, wenn sie den bösen Loki und seine Alien-Armee darin hindern wollen, die Menschheit zu versklaven.",
        imageUrl = "https://www.film-rezensionen.de/wp-content/uploads/2015/04/Marvels-The-Avengers-Frontpage.jpg"
    ),
)


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
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)) {

        items(sampleMovies.size) { index ->
            MovieImageCard(sampleMovies[index])
        }
    }
}

@Composable
fun MovieImageCard(movie: Movie) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Image(
                painter = rememberImagePainter(
                    data = movie.imageUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = "${movie.title} Image",
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = movie.title)
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropDown,
                        contentDescription = "Toggle Details",
                        modifier = Modifier
                            .graphicsLayer {
                                rotationZ = if (expanded) 180f else 0f
                }
                    )
            }}
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Director: ${movie.director}", style = MaterialTheme.typography.bodyMedium)
                    Text("Released: ${movie.year}", style = MaterialTheme.typography.bodyMedium)

                    Divider(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                    modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text("Plot: ${movie.plot}", style = MaterialTheme.typography.bodyMedium)
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
