package com.e.data

import com.e.data.repository.FavouriteRepository
import com.e.data.source.FavouriteDataSource
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
class FavouriteRepositoryTest {

    @Mock
    lateinit var favouriteDataSource: FavouriteDataSource

    lateinit var favouriteRepository: FavouriteRepository

    @Before
    fun setUp() {
        favouriteRepository = FavouriteRepository(favouriteDataSource)
    }

    @Test
    fun `get favourite films of local data`() {
        runBlocking {

            val remoteMovies = listOf(mockedMovie.copy())
            whenever(favouriteDataSource.getFavourites()).thenReturn(remoteMovies)

            val movies = favouriteRepository.getFavouritesMovies()

            Assert.assertEquals(remoteMovies, movies)
        }
    }
}