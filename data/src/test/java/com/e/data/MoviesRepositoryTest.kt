package com.e.data

import com.e.data.repository.DateRepository
import com.e.data.repository.MoviesRepository
import com.e.data.repository.RegionRepository
import com.e.data.source.RemoteDataSource
import com.e.testshared.mockedMovie
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var dateRepository: DateRepository

    @Mock
    lateinit var regionRepository: RegionRepository

    lateinit var moviesRepository: MoviesRepository

    private val apiKey = "1a2b3c4d"

    @Before
    fun setUp() {
        moviesRepository = MoviesRepository(remoteDataSource, dateRepository, regionRepository, apiKey)
    }

    @Test
    fun `get favourite films from local data`() {
        runBlocking {

            val remoteMovies = listOf(mockedMovie.copy())
            whenever(remoteDataSource.getReleaseFilms(any(), any(), any(), any(), any())).thenReturn(remoteMovies)
            whenever(regionRepository.findLastRegion()).thenReturn("US")
            whenever(dateRepository.finalReleaseFilmDate()).thenReturn("2020-01-10")
            whenever(dateRepository.releaseFilmDate()).thenReturn("2020-01-20")
            whenever(dateRepository.releaseType()).thenReturn("1|2")

            val movies = moviesRepository.getReleaseFilms()

            Assert.assertEquals(remoteMovies, movies)
        }
    }

    @Test
    fun `get trending films of remote data`() {
        runBlocking {

            val remoteMovies = listOf(mockedMovie.copy())
            whenever(remoteDataSource.findTrendingMovies(any(), any(), any(), any())).thenReturn(remoteMovies)
            whenever(regionRepository.findLastRegion()).thenReturn("US")

            val movies = moviesRepository.findTrendingFilms()

            Assert.assertEquals(remoteMovies, movies)
        }
    }
}