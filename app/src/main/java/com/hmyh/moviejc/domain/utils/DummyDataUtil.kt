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