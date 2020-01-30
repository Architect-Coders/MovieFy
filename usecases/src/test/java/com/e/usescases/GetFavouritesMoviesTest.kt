package com.e.usescases

import com.e.data.repository.FavouriteRepository
import com.e.testshared.mockedMovie
import com.e.usecases.GetFavouritesMovies
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetFavouritesMoviesTest {
    @Mock
    lateinit var favouriteRepository: FavouriteRepository

    lateinit var getFavourites: GetFavouritesMovies

    @Before
    fun setUp() {
        getFavourites = GetFavouritesMovies(favouriteRepository)
    }

    @Test
    fun `invoke calls get favourites repository`() {
        runBlocking {

            val movie = listOf(mockedMovie.copy())
            whenever(favouriteRepository.getFavouritesMovies()).thenReturn(movie)

            val result = getFavourites.invoke()

            Assert.assertEquals(movie, result)
        }
    }
}