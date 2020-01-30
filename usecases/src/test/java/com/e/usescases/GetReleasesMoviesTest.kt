package com.e.usescases

import com.e.data.repository.MoviesRepository
import com.e.usecases.GetReleasesMovies
import com.e.testshared.mockedMovie
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetReleasesMoviesTest {
    @Mock
    lateinit var moviesRepository: MoviesRepository

    lateinit var getReleasesMovies: GetReleasesMovies

    @Before
    fun setUp() {
        getReleasesMovies = GetReleasesMovies(moviesRepository)
    }

    @Test
    fun `invoke calls get releases movies repository`() {
        runBlocking {

            val movie = listOf(mockedMovie.copy())
            whenever(moviesRepository.getReleaseFilms()).thenReturn(movie)

            val result = getReleasesMovies.invoke()

            Assert.assertEquals(movie, result)
        }
    }
}