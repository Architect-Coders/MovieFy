package com.moviefy.di

import android.app.Application
import com.e.data.repository.DateRepository
import com.e.data.repository.MoviesRepository
import com.e.data.repository.RegionRepository
import com.e.data.source.FavouriteDataSource
import com.e.data.source.LocationDataSource
import com.e.data.source.PermissionChecker
import com.e.data.source.RemoteDataSource
import com.e.usecases.*
import com.moviefy.R
import com.moviefy.data.AndroidPermissionChecker
import com.moviefy.data.PlayServicesLocationDatasource
import com.moviefy.data.database.FilmDatabase
import com.moviefy.data.database.RoomDataSource
import com.moviefy.data.server.MovieDataSource
import com.moviefy.data.server.MovieDb
import com.moviefy.ui.detailMovie.DetailMoviePresenter
import com.moviefy.ui.detailMovie.DetailMovieView
import com.moviefy.ui.favourites.FavouritePresenter
import com.moviefy.ui.favourites.FavouriteView
import com.moviefy.ui.releaseFilms.ReleaseFilmsPresenter
import com.moviefy.ui.releaseFilms.ReleaseFilmsView
import com.moviefy.ui.trending.TrendingMoviesPresenter
import com.moviefy.ui.trending.TrendingMoviesView
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("apiKey")) { androidApplication().getString(R.string.api_key) }
    single { FilmDatabase.build(get()) }
    factory<FavouriteDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { MovieDataSource(get()) }
    factory<LocationDataSource> { PlayServicesLocationDatasource(get()) }
    factory<PermissionChecker> { AndroidPermissionChecker(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "https://api.themoviedb.org/3/" }
    single { MovieDb(get(named("baseUrl"))) }
}

val dataModule = module {
    factory { DateRepository() }
    factory { RegionRepository(get(), get()) }
    factory { MoviesRepository(get(), get(), get(), get(), get(named("apiKey"))) }
}

val scopesModule = module {
    factory { (view: TrendingMoviesView) -> TrendingMoviesPresenter(view, get(), get(), get()) }
    factory { FindTrendingMovies(get()) }

    factory { (view: ReleaseFilmsView) -> ReleaseFilmsPresenter(view, get(), get(), get()) }
    factory { GetReleasesMovies(get()) }
    factory { RemoveFavouriteMovie(get()) }
    factory { AddFavouriteMovie(get()) }

    factory { (view: FavouriteView) -> FavouritePresenter(view, get(), get(), get()) }
    factory { GetFavouritesMovies(get()) }

    factory { (view: DetailMovieView) -> DetailMoviePresenter(view) }
}