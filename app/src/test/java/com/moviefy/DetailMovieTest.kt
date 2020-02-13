package com.moviefy

import android.content.Context
import com.e.testshared.mockedMovie
import com.moviefy.data.toMovieUi
import com.moviefy.di.scopesModule
import com.moviefy.ui.detailMovie.DetailMoviePresenter
import com.moviefy.ui.detailMovie.DetailMovieView
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
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
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieTest: KoinTest {

    private val context: Context = mock()
    private val view: DetailMovieView = mock()
    private val presenter: DetailMoviePresenter by inject { parametersOf(view) }

    @Before
    fun setUp() {
        startKoin {
            androidContext(context)
            modules(scopesModule)
        }
    }

    @Test
    fun `onInit show detail data`() {

        val movie = mockedMovie.toMovieUi()

        presenter.onCreate(movie)
        verify(view).updateUI(movie)
    }

    @After
    fun after() {
        stopKoin()
    }
}