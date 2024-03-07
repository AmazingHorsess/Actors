package com.example.actors.ui.screens.actorDetails.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actors.R
import com.example.actors.data.model.ActorDetail
import com.example.actors.ui.animations.borderRevealAnimation
import com.example.actors.ui.theme.ActorsTheme
import com.example.actors.utils.calculateAge
import com.example.actors.utils.getPlaceOfBirth
import com.example.actors.utils.getPopularity

/**
 * Row with 3 elements which shows actor details just below actor image.
 * 1. Age of actor
 * 2. Popularity
 * 3. Place of birth
 */
@Composable
internal fun ActorInfoHeader(
    actorData: ActorDetail?
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        item { AgeInfo(actorAge = actorData?.dateOfBirth) }
        item { PopularityInfo(popularity = actorData?.popularity) }
        item { CountryInfo(placeOfBirth = actorData?.placeOfBirth) }
    }
}

/**
 * First element in [ActorInfoHeader].
 * Shows calculated age of actor from [calculateAge]
 */
@Composable
private fun AgeInfo(
    actorAge: String?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier.borderRevealAnimation()
        ) {
            Text(
                text = "${calculateAge(actorAge)}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }

        ActorInfoHeaderSubtitle(
            subtitle = stringResource(id = R.string.actor_age_subtitle)
        )
    }
}

/**
 * Second element in [ActorInfoHeader].
 * Fetches actors popularity from function [calculateAge].
 */
@Composable
private fun PopularityInfo(
    popularity: Double?
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.borderRevealAnimation()
        ) {
            Text(
                text = getPopularity(popularity),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(alignment = Alignment.Center)
            )
        }

        ActorInfoHeaderSubtitle(
            subtitle = stringResource(id = R.string.actor_popularity_subtitle )
        )
    }
}

/**
 * Third element in [ActorInfoHeader].
 * Fetches place of birth from function [calculateAge].
 */
@Composable
private fun CountryInfo(
    placeOfBirth: String?
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.borderRevealAnimation()
        ) {
            Image(
                painterResource(id = R.drawable.ic_globe),
                contentDescription = "stringResource(2",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .size(32.dp)
            )
        }

        ActorInfoHeaderSubtitle(
            subtitle = "${getPlaceOfBirth(placeOfBirth)}"
        )
    }
}

@Composable
private fun ActorInfoHeaderSubtitle(
    subtitle: String
) {
    Text(
        text = subtitle,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 8.dp),
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview
@Composable
private fun ActorInfoHeaderPreview() {
    ActorsTheme {
        ActorInfoHeader(
            actorData = ActorDetail(
                actorId = 1,
                actorName = "Kate WinsletKate Winslet",
                profileUrl = "",
                biography = "Kate Elizabeth Winslet CBE born 5 October 1975 is an English actress. Known for her work in independent films, particularly period dramas, and for her portrayals of headstrong and complicated women, she has received numerous accolades, including an Academy Award, a Grammy Award, two Primetime Emmy Awards, three BAFTA Awards, and five Golden Globe Awards. Time magazine named Winslet one of the 100 most influential people in the world in 2009 and 2021. She was appointed Commander of the Order of the British Empire (CBE) in 2012.",
                dateOfBirth = "47",
                placeOfBirth = "UK",
                popularity = 52.0
            )
        )
    }
}