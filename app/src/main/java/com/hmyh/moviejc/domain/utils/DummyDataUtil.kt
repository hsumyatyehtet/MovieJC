package com.hmyh.moviejc.domain.utils

import com.hmyh.moviejc.domain.feature.home.model.NowPlayingMovieVO
import com.hmyh.moviejc.domain.feature.moviedetail.model.MovieDetail

var movieDummyVO = NowPlayingMovieVO(
    id = 1,
    originalTitle = "Sample Movie",
    popularity = 8.5f,
    posterPath = "/7HqLLVjdjhXS0Qoz1SgZofhkIpE.jpg",
    title ="Sample Movie",
    releaseDate = "2025-01-01",
    voteCount = 1000
)

var movieDetailVO = MovieDetail(
    id = 1,
    backDropPack = "/7HqLLVjdjhXS0Qoz1SgZofhkIpE.jpg",
    originalTitle = "Sample Movie",
    homePage = "https://www.samplemovie.com",
    originalCountry = listOf("US"),
    overView = "This is a sample movie overview.",
    posterPath = "/7HqLLVjdjhXS0Qoz1SgZofhkIpE.jpg",
    releaseDate = "2025-01-01",
    title = "Sample Movie",
    voteAverage = "8.5",
    genreList = listOf(
        MovieDetail.GenreEntity(id = 1, name = "Action"),
        MovieDetail.GenreEntity(id = 2, name = "Adventure")
    ),
    runtime = 120
)

val searchMovieDummyList = listOf(
    NowPlayingMovieVO(
        id = 1,
        originalTitle = "Sample Movie",
        popularity = 8.5f,
        posterPath = "/7HqLLVjdjhXS0Qoz1SgZofhkIpE.jpg",
        title = "Sample Movie",
        releaseDate = "2025-01-01",
        voteCount = 1000
    ),
    NowPlayingMovieVO(
        id = 2,
        originalTitle = "Inception",
        popularity = 9.2f,
        posterPath = "/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
        title = "Inception",
        releaseDate = "2010-07-16",
        voteCount = 32000
    ),
    NowPlayingMovieVO(
        id = 3,
        originalTitle = "Interstellar",
        popularity = 9.0f,
        posterPath = "/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
        title = "Interstellar",
        releaseDate = "2014-11-07",
        voteCount = 28000
    ),
    NowPlayingMovieVO(
        id = 4,
        originalTitle = "The Dark Knight",
        popularity = 9.5f,
        posterPath = "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
        title = "The Dark Knight",
        releaseDate = "2008-07-18",
        voteCount = 35000
    ),
    NowPlayingMovieVO(
        id = 5,
        originalTitle = "Avatar",
        popularity = 8.8f,
        posterPath = "/jRXYjXNq0Cs2TcJjLgkPSFaWpI4.jpg",
        title = "Avatar",
        releaseDate = "2009-12-18",
        voteCount = 27000
    ),
    NowPlayingMovieVO(
        id = 6,
        originalTitle = "Dune",
        popularity = 8.1f,
        posterPath = "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
        title = "Dune",
        releaseDate = "2021-10-22",
        voteCount = 12000
    )
)