package com.e.usescases

import com.e.data.repository.MoviesRepository
import com.e.testshared.mockedMovie
import com.e.usecases.RemoveFavouriteMovie
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoveFavouriteMovieTest {
    @Mock
    lateinit var moviesRepository: MoviesRepository

    lateinit var removeFavouriteMovie: RemoveFavouriteMovie

    @Before
    fun setUp() {
        removeFavouriteMovie = RemoveFavouriteMovie(moviesRepository)
    }

    @Test
    fun `invoke calls add favourites repository`() {
        runBlocking {

            val movie = mockedMovie.copy()
            removeFavouriteMovie.invoke(movie)

            verify(moviesRepository).removeFavouriteMovie(movie)
        }
    }
}