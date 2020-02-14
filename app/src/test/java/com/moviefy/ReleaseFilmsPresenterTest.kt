package com.moviefy

import android.content.Context
import com.e.data.repository.MoviesRepository
import com.e.data.source.RemoteDataSource
import com.e.testshared.mockedMovie
import com.e.usecases.*
import com.moviefy.data.toMovieUi
import com.moviefy.di.dataModule
import com.moviefy.di.scopesModule
import com.moviefy.ui.releaseFilms.ReleaseFilmsPresenter
import com.moviefy.ui.releaseFilms.ReleaseFilmsView
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
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
class ReleaseFilmsPresenterTest: KoinTest {

    private val context: Context = mock()

    private var getReleasesMovies: GetReleasesMovies = mock()

    private var view: ReleaseFilmsView = mock()

    private val presenter: ReleaseFilmsPresenter by inject { parametersOf(view) }

    @Before
    fun setUp() {
        startKoin {
            androidContext(context)
            modules(scopesModule)
        }
        declareMock<AddFavouriteMovie>()
        declareMock<RemoveFavouriteMovie>()
        declareMock<GetReleasesMovies>()
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
            whenever(getReleasesMovies.invoke()).thenReturn(domainMovies)
            assertEquals("same id", 1, domainMovies[0].id)
        }
    }

    @After
    fun after() {
        stopKoin()
    }
}