package com.e.usescases

import com.e.data.repository.MoviesRepository
import com.e.testshared.mockedMovie
import com.e.usecases.FindTrendingMovies
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindTrendingMoviesTest {

    @Mock
    lateinit var moviesRepository: MoviesRepository

    lateinit var findReleasesMovies: FindTrendingMovies

    @Before
    fun setUp() {
        findReleasesMovies = FindTrendingMovies(moviesRepository)
    }

    @Test
    fun `invoke calls get trending movies repository`() {
        runBlocking {

            val movie = listOf(mockedMovie.copy())
            whenever(moviesRepository.findTrendingFilms()).thenReturn(movie)

            val result = findReleasesMovies.invoke()

            Assert.assertEquals(movie, result)
        }
    }
}