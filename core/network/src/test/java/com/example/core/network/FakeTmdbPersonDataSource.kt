package com.example.core.network

import JvmUnitTestFakeAssetManager
import com.example.core.network.fake.FakeTmdbPersonDataSource
import com.example.core.network.model.KnownForPopularDTO
import com.example.core.network.model.KnownForTrendingDTO
import com.example.core.network.model.PersonDetailDTO
import com.example.core.network.model.PopularPersonResponseDTO
import com.example.core.network.model.PopularPersonResultsDTO
import com.example.core.network.model.TrendingPersonResponseDTO
import com.example.core.network.model.TrendingPersonsResultsDTO
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

class FakeTmdbPersonDataSourceTest {

    private lateinit var subject: FakeTmdbPersonDataSource


    private val testDispatcher = StandardTestDispatcher()
    @Before
    fun setUp(){
        subject = FakeTmdbPersonDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestFakeAssetManager
        )
    }
    @Test
    fun testDeserializationOfDetailPerson()= runTest(testDispatcher)
    {
        assertEquals(
            PersonDetailDTO(
                adult = false,
                alsoKnownAs = listOf("Thomas Jeffrey Hanks", "Том Хэнкс", "توم هانكس", "トム・ハンクス", "톰 행크스", "ทอม แฮงส์", "汤姆·汉克斯", "Том Генкс", "Том Хенкс", "Томас Джеффрі Генкс", "Τομ Χανκς", "टॉम हैंक्स", "ടോം ഹാങ്ക്സ്", "湯姆‧漢克斯", "湯姆·漢克"),
                biography = "Thomas Jeffrey Hanks (born July 9, 1956) is an American actor and filmmaker. Known for both his comedic and dramatic roles, Hanks is one of the most popular and recognizable film stars worldwide, and is widely regarded as an American cultural icon. Hanks made his breakthrough with leading roles in the comedies Splash (1984) and Big (1988). He won two consecutive Academy Awards for Best Actor for starring as a gay lawyer suffering from AIDS in Philadelphia (1993) and a young man with below-average IQ in Forrest Gump (1994). Hanks collaborated with film director Steven Spielberg on five films: Saving Private Ryan (1998), Catch Me If You Can (2002), The Terminal (2004), Bridge of Spies (2015), and The Post (2017), as well as the 2001 miniseries Band of Brothers, which launched him as a director, producer, and screenwriter. Hanks' other notable films include the romantic comedies Sleepless in Seattle (1993) and You've Got Mail (1998); the dramas Apollo 13 (1995), The Green Mile (1999), Cast Away (2000), Road to Perdition (2002), and Cloud Atlas (2012); and the biographical dramas Saving Mr. Banks (2013), Captain Phillips (2013), Sully (2016), and A Beautiful Day in the Neighborhood (2019). He has also appeared as the title character in the Robert Langdon film series, and has voiced Sheriff Woody in the Toy Story film series. Description above from the Wikipedia article Tom Hanks, licensed under CC-BY-SA, full list of contributors on Wikipedia.",
                birthday = "1956-07-09",
                deathday = null,
                gender = 2,
                homepage = null,
                id = 31,
                imdbId = "nm0000158",
                knownForDepartment = "Acting",
                name = "Tom Hanks",
                placeOfBirth = "Concord, California, USA",
                popularity = 82.989,
                profilePath = "/xndWFsBlClOJFRdhSt4NBwiPq2o.jpg"

            ),

            subject.getDetailPerson(31)
        )
    }
    @Test
    fun testDeserializationOfPopularPersons() = runTest(testDispatcher){
        assertEquals(
                    PopularPersonResponseDTO(
                        page = 1,
                        results = PopularPersonResultsDTOList,
                        totalPages = 500,
                        totalResults = 10000
                    ),
            subject.getPopularPersons()
        )

    }
    @Test
    fun testDeserializationOfTrendingPersons() = runTest(testDispatcher){
        assertEquals(
            TrendingPersonResponseDTO(
                page = 1,
                results = trendingPersonsResultsDTO,
                totalPages = 1000,
                totalResults = 20000
            ),
            subject.getTrendingPersons()
        )
    }

    /**
     * A companion object holding instances of [KnownForPopularDTO] for popular films featuring Jason Statham.
     */
    companion object {
        /**
         * An instance of [KnownForPopularDTO] representing the film "Snatch".
         */
        private val snatchFilm: KnownForPopularDTO = KnownForPopularDTO(
            adult = false,
            backdropPath = "/ysKahAEPP8h6MInuLjr0xuZOTjh.jpg",
            genreIds = listOf(80,35),
            id = 107,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "Snatch",
            overview = "Unscrupulous boxing promoters, violent bookmakers, a Russian gangster, incompetent amateur robbers and supposedly Jewish jewelers fight to track down a priceless stolen diamond.",
            posterPath = "/56mOJth6DJ6JhgoE2jtpilVqJO.jpg",
            title = "Snatch",
            video = false,
            voteAverage = 7.8,
            voteCount = 7985,
            releaseDate = "2000-09-01"
        )
        /**
         * An instance of [KnownForPopularDTO] representing the film "The Meg".
         */
        private val theMagFilm: KnownForPopularDTO =  KnownForPopularDTO(
            adult = false,
            backdropPath = "/2uSCHUsmzb6KkQPFSxBQ7bgfJLE.jpg",
            genreIds = listOf(28,878,27),
            id = 345940,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "The Meg",
            overview = "A deep sea submersible pilot revisits his past fears in the Mariana Trench, and accidentally unleashes the seventy foot ancestor of the Great White Shark believed to be extinct.",
            posterPath = "/xqECHNvzbDL5I3iiOVUkVPJMSbc.jpg",
            releaseDate = "2018-08-09",
            title = "The Meg",
            video = false,
            voteAverage = 6.2,
            voteCount = 6367
        )
        /**
         * An instance of [KnownForPopularDTO representing the film "The Fate of the Furious".
         */
        private val theFateOfTheFurious: KnownForPopularDTO =  KnownForPopularDTO(
            id = 337339,
            adult = false,
            backdropPath = "/jzdnhRhG0dsuYorwvSqPqqnM1cV.jpg",
            genreIds = listOf(28,80,53),
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "The Fate of the Furious",
            overview = "When a mysterious woman seduces Dom into the world of crime and a betrayal of those closest to him, the crew face trials that will test them as never before.",
            posterPath = "/dImWM7GJqryWJO9LHa3XQ8DD5NH.jpg",
            releaseDate = "2017-04-12",
            title = "The Fate of the Furious",
            video = false,
            voteAverage = 6.9,
            voteCount = 9426
        )
        /**
         * A list of the known films featuring Jason Statham.
         */
        private val popularJasonStathamKnownForList = listOf<KnownForPopularDTO>(
            snatchFilm, theMagFilm, theFateOfTheFurious
        )
        /**
         * An instance of [PopularPersonResultsDTO] representing Jason Statham.
         */
        private val jasonStathamPopularPersonResultsDTO: PopularPersonResultsDTO = PopularPersonResultsDTO(
            adult = false,
            gender = 2,
            id = 976,
            knownFor = popularJasonStathamKnownForList,
            knownForDepartment = "Acting",
            name = "Jason Statham",
            popularity = 104.266,
            profilePath = "/whNwkEQYWLFJA8ij0WyOOAD5xhQ.jpg",

        )
        /**
         * An instance of [nownForPopularDTO] representing the film "Blade Runner 2049".
         */
        private val bladeRunner: KnownForPopularDTO = KnownForPopularDTO(
            adult = false,
            backdropPath = "/ilRyazdMJwN05exqhwK4tMKBYZs.jpg",
            genreIds = listOf(878,18),
            id = 335984,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "Blade Runner 2049",
            overview = "Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos. K's discovery leads him on a quest to find Rick Deckard, a former LAPD blade runner who has been missing for 30 years.",
            posterPath = "/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg",
            releaseDate = "2017-10-04",
            title = "Blade Runner 2049",
            video = false,
            voteAverage = 7.5,
            voteCount = 11771
        )
        private val trendingBladeRunner = KnownForTrendingDTO(
            adult = false,
            backdropPath = "/ilRyazdMJwN05exqhwK4tMKBYZs.jpg",
            genreIds = listOf(878,18),
            id = 335984,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "Blade Runner 2049",
            overview = "Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos. K's discovery leads him on a quest to find Rick Deckard, a former LAPD blade runner who has been missing for 30 years.",
            posterPath = "/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg",
            releaseDate = "2017-10-04",
            title = "Blade Runner 2049",
            video = false,
            voteAverage = 7.531,
            voteCount = 11771,
            popularity = 79.571

        )
        /**
         * An instance of [KnownForPopularDTO] representing the film "Knives Out".
         */
        private val knivesOut:KnownForPopularDTO = KnownForPopularDTO(
            adult = false,
            backdropPath = "/4HWAQu28e2yaWrtupFPGFkdNU7V.jpg",
            genreIds = listOf(35,80,9648),
            id = 546554,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "Knives Out",
            overview = "When renowned crime novelist Harlan Thrombey is found dead at his estate just after his 85th birthday, the inquisitive and debonair Detective Benoit Blanc is mysteriously enlisted to investigate. From Harlan's dysfunctional family to his devoted staff, Blanc sifts through a web of red herrings and self-serving lies to uncover the truth behind Harlan's untimely death.",
            posterPath = "/pThyQovXQrw2m0s9x82twj48Jq4.jpg",
            releaseDate = "2019-11-27",
            title = "Knives Out",
            video = false,
            voteAverage = 7.9,
            voteCount = 10631
        )
        private val trendingKnivesOut = KnownForTrendingDTO(
            adult = false,
            backdropPath = "/4HWAQu28e2yaWrtupFPGFkdNU7V.jpg",
            genreIds = listOf(35,80,9648),
            id = 546554,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "Knives Out",
            overview = "When renowned crime novelist Harlan Thrombey is found dead at his estate just after his 85th birthday, the inquisitive and debonair Detective Benoit Blanc is mysteriously enlisted to investigate. From Harlan's dysfunctional family to his devoted staff, Blanc sifts through a web of red herrings and self-serving lies to uncover the truth behind Harlan's untimely death.",
            posterPath = "/pThyQovXQrw2m0s9x82twj48Jq4.jpg",
            releaseDate = "2019-11-27",
            title = "Knives Out",
            video = false,
            voteAverage = 7.854,
            voteCount = 10630,
            popularity = 52.748,

        )
        /**
         * An instance of [KnownForPopularDTO] representing the film "War Dogs".
         */
        private val warDogs: KnownForPopularDTO = KnownForPopularDTO(
            adult = false,
            backdropPath = "/3Mnbw4gOYz5BNZB9PhZ2cyBSQum.jpg",
            genreIds = listOf(35,80,18),
            id = 308266,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "War Dogs",
            overview = "Based on the true story of two young men, David Packouz and Efraim Diveroli, who won a $300 million contract from the Pentagon to arm America's allies in Afghanistan.",
            posterPath = "/uH9qGH5XS1iZXCSb3tgu40dxQoh.jpg",
            releaseDate = "2016-08-17",
            title = "War Dogs",
            video = false,
            voteAverage = 6.9,
            voteCount = 4324
        )

        private val trendingWarDogs = KnownForTrendingDTO(
            adult = false,
            backdropPath = "/3Mnbw4gOYz5BNZB9PhZ2cyBSQum.jpg",
            genreIds = listOf(35,80,18),
            id = 308266,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "War Dogs",
            overview = "Based on the true story of two young men, David Packouz and Efraim Diveroli, who won a $300 million contract from the Pentagon to arm America's allies in Afghanistan.",
            posterPath = "/uH9qGH5XS1iZXCSb3tgu40dxQoh.jpg",
            releaseDate = "2016-08-17",
            title = "War Dogs",
            video = false,
            voteAverage = 6.892,
            voteCount = 4324,
            popularity = 29.906

        )
        /**
         * A list of the known films featuring Ana De Armas.
         */
        private val anaDeArmasKnownForList = listOf(
            bladeRunner, knivesOut, warDogs

        )
        /**
         * An instance of [PopularPersonResultsDTO] representing Ana De Armas".
         */
        private val popularAnaDeArmasPersonResultsDTO: PopularPersonResultsDTO = PopularPersonResultsDTO(
            adult = false,
            gender = 1,
            id = 224513,
            knownFor = anaDeArmasKnownForList,
            knownForDepartment = "Acting",
            name = "Ana de Armas",
            popularity = 343.33,
            profilePath = "/3vxvsmYLTf4jnr163SUlBIw51ee.jpg"
        )
        /**
         * A list of instances of [PopularPersonResultsDTO]
         */
        private val PopularPersonResultsDTOList: List<PopularPersonResultsDTO> = listOf(
            jasonStathamPopularPersonResultsDTO,popularAnaDeArmasPersonResultsDTO
        )
        private val trendingAnaDeArmasKnownFor = listOf(
            trendingBladeRunner, trendingKnivesOut, trendingWarDogs
        )
        private val theGodfatherPartII = KnownForTrendingDTO(
            adult = false,
            backdropPath = "/kGzFbGhp99zva6oZODW5atUtnqi.jpg",
            id = 240,
            title = "The Godfather Part II",
            originalLanguage = "en",
            originalTitle = "The Godfather Part II",
            overview = "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York. In the 1950s, Michael Corleone attempts to expand the family business into Las Vegas, Hollywood and Cuba.",
            posterPath = "/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg",
            mediaType = "movie",
            genreIds = listOf(18,80),
            releaseDate = "1974-12-20",
            video = false,
            voteAverage = 8.597,
            voteCount = 10775,
            popularity = 62.221
            )

        private val scarface = KnownForTrendingDTO(
            adult = false,
            backdropPath = "/cCvp5Sni75agCtyJkNOMapORUQV.jpg",
            id = 111,
            title = "Scarface",
            originalLanguage = "en",
            originalTitle = "Scarface",
            overview = "After getting a green card in exchange for assassinating a Cuban government official, Tony Montana stakes a claim on the drug trade in Miami. Viciously murdering anyone who stands in his way, Tony eventually becomes the biggest drug lord in the state, controlling nearly all the cocaine that comes through Miami. But increased pressure from the police, wars with Colombian drug cartels and his own drug-fueled paranoia serve to fuel the flames of his eventual downfall.",
            posterPath = "/iQ5ztdjvteGeboxtmRdXEChJOHh.jpg",
            mediaType = "movie",
            genreIds = listOf(28,80,18),
            releaseDate = "1983-12-09",
            video = false,
            voteAverage = 8.166,
            voteCount = 10271,
            popularity = 76.345
        )
        private val theGodfather = KnownForTrendingDTO(
            adult = false,
            backdropPath = "/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            id = 238,
            title = "The Godfather",
            originalLanguage = "en",
            originalTitle = "The Godfather",
            overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
            posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            mediaType = "movie",
            genreIds = listOf(18,80),
            releaseDate = "1972-03-14",
            video = false,
            voteAverage = 8.712,
            voteCount = 17806,
            popularity = 102.215
        )
        private val trendingAlPacinoKnownFor = listOf(
            theGodfatherPartII, scarface, theGodfather

        )
        private val trendingPersonsResultsDTOAnaDeArmas: TrendingPersonsResultsDTO = TrendingPersonsResultsDTO(
            adult = false,
            id = 224513,
            name = "Ana de Armas",
            originalName = "Ana de Armas",
            mediaType = "person",
            popularity = 349.766,
            gender = 1,
            knownForDepartment = "Acting",
            profilePath = "/3vxvsmYLTf4jnr163SUlBIw51ee.jpg",
            knownFor = trendingAnaDeArmasKnownFor,

        )
        private val trendingPersonResultsDTOAlPacino = TrendingPersonsResultsDTO(
            adult = false,
            id = 1158,
            name = "Al Pacino",
            originalName = "Al Pacino",
            mediaType = "person",
            popularity = 47.533,
            gender = 2,
            knownForDepartment = "Acting",
            profilePath = "/fMDFeVf0pjopTJbyRSLFwNDm8Wr.jpg",
            knownFor = trendingAlPacinoKnownFor,

        )

        private val trendingPersonsResultsDTO: List<TrendingPersonsResultsDTO> = listOf(
            trendingPersonsResultsDTOAnaDeArmas, trendingPersonResultsDTOAlPacino


        )










    }

}
