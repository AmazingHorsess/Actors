package com.example.actors.ui.modalSheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actors.R
import com.example.actors.data.datasource.fake.fakeMovieDetail
import com.example.actors.data.model.MovieDetail
import com.example.actors.ui.components.CircularSeparator
import com.example.actors.ui.components.LoadNetworkImage
import com.example.actors.ui.screens.movieDetails.composables.MovieGenre
import com.example.actors.ui.theme.ActorsTheme
import com.example.actors.utils.getMovieRuntimeFormatted

@Composable
fun SheetContentMovieDetails(
    movie: MovieDetail?,
    navigateToSelectedMovie: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        HeaderModalSheet(movie, navigateToSelectedMovie)
        Spacer(modifier = Modifier.height(4.dp))
        SeparatorSheetTitleHeader()
        Spacer(modifier = Modifier.height(16.dp))
        MovieGenre(genres = movie?.genres)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularSeparator()
            MovieReleaseDateText(movie?.releaseDate)
            CircularSeparator()
            MovieDurationText(movie?.runtime)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            MoviePosterImage(movie, navigateToSelectedMovie)
            MovieOverviewText(movie?.overview.toString())
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun HeaderModalSheet(
    movie: MovieDetail?,
    onClickMovie: (Int) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        Text(
            text = "${movie?.movieTitle}",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(8f)
                .align(alignment = Alignment.CenterVertically)
        )

        // On clicking this icon triggers detail screen navigation with movie Id.
        IconButton(
            onClick = {
                if (movie != null) {
                    onClickMovie(movie.movieId)
                }
            },
            modifier = Modifier
                .weight(2f)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                contentDescription = ""
            )
        }
    }
}
@Composable
private fun SeparatorSheetTitleHeader() {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 0.50.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)
    )
}
@Composable
private fun MovieReleaseDateText(
    releaseDate: String?
) {
    Text(
        text = "${releaseDate?.take(4)}",
        color = MaterialTheme.colorScheme.onSurface.copy(0.7f),
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
    )
}
@Composable
private fun MovieDurationText(
    runtime: Int?
) {
    Text(
        text = getMovieRuntimeFormatted(runtime),
        color = MaterialTheme.colorScheme.onSurface.copy(0.7f),
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
    )
}
@Composable
private fun MoviePosterImage(
    movie: MovieDetail?,
    selectedMovie: (Int) -> Unit
) {
    LoadNetworkImage(
        imageUrl = "${movie?.poster}",
        contentDescription = "",
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .size(100.dp, 150.dp)
            .clickable {
                if (movie != null) {
                    selectedMovie(movie.movieId)
                }
            }
    )
}
@Composable
private fun MovieOverviewText(
    overview: String
) {
    Text(
        text = overview,
        maxLines = 7,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        style = TextStyle(
            lineHeight = 20.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(0.8f),
            textAlign = TextAlign.Justify,
            fontSize = 16.sp
        )
    )
}
@Preview(showBackground = true)
@Composable
private fun SheetContentMovieDetailsLightPreview() {
    ActorsTheme(darkTheme = false) {
        SheetContentMovieDetails(
            movie = fakeMovieDetail,
            navigateToSelectedMovie = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF211a18)
@Composable
private fun SheetContentMovieDetailsDarkPreview() {
    ActorsTheme(darkTheme = true) {
        SheetContentMovieDetails(
            movie = fakeMovieDetail,
            navigateToSelectedMovie = {}
        )
    }
}

