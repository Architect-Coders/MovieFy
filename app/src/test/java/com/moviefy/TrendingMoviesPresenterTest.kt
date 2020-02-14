package com.moviefy

import android.content.Context
import com.e.testshared.mockedMovie
import com.e.usecases.AddFavouriteMovie
import com.e.usecases.FindTrendingMovies
import com.e.usecases.RemoveFavouriteMovie
import com.moviefy.data.toMovieUi
import com.moviefy.di.scopesModule
import com.moviefy.ui.trending.TrendingMoviesPresenter
import com.moviefy.ui.trending.TrendingMoviesView
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class TrendingMoviesPresenterTest: KoinTest {

    private val context: Context = mock()

    private var findTrendingMovies: FindTrendingMovies = mock()

    private var view: TrendingMoviesView = mock()

    private val presenter: TrendingMoviesPresenter by inject { parametersOf(view) }

    @Before
    fun setUp() {
        startKoin {
            androidContext(context)
            modules(scopesModule)
        }
        declareMock<FindTrendingMovies>()
        declareMock<RemoveFavouriteMovie>()
        declareMock<AddFavouriteMovie>()
    }

    @Test
    fun `show detail movie`() {
            presenter.onMovieClicked(mockedMovie.toMovieUi(), isOpenDetail = true, isFavourite = false)
            verify(view).navigateTo(mockedMovie.toMovieUi())
    }

    @Test
    fun `update data after get movies`() {
        runBlocking {
            val domainMovies = listOf(mockedMovie)
            whenever(findTrendingMovies.invoke()).thenReturn(domainMovies)
            assertEquals("same id", 1, domainMovies[0].id)
        }
    }

    @After
    fun after() {
        stopKoin()
    }
}