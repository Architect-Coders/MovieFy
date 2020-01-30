package com.moviefy.di

import android.app.Application
import com.e.data.repository.DateRepository
import com.e.data.repository.FavouriteRepository
import com.e.data.repository.MoviesRepository
import com.e.data.repository.RegionRepository
import com.e.data.source.FavouriteDataSource
import com.e.data.source.LocationDataSource
import com.e.data.source.PermissionChecker
import com.e.data.source.RemoteDataSource
import com.e.usecases.FindTrendingMovies
import com.e.usecases.GetFavouritesMovies
import com.e.usecases.GetReleasesMovies
import com.moviefy.R
import com.moviefy.data.AndroidPermissionChecker
import com.moviefy.data.PlayServicesLocationDatasource
import com.moviefy.data.database.FilmDatabase
import com.moviefy.data.database.RoomDataSource
import com.moviefy.data.server.MovieDataSource
import com.moviefy.data.server.MovieDb
import com.moviefy.ui.favourites.FavouritePresenter
import com.moviefy.ui.releaseFilms.ReleaseFilmsPresenter
import com.moviefy.ui.trending.TrendingFilmsPresenter
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
    factory { MoviesRepository(get(), get(), get(), get(named("apiKey"))) }
    factory { FavouriteRepository(get()) }

}

private val scopesModule = module {
    factory { (view: TrendingFilmsPresenter.View) -> TrendingFilmsPresenter(get(), get()) }
    factory { FindTrendingMovies(get()) }

    factory { (view: ReleaseFilmsPresenter.View) -> ReleaseFilmsPresenter(get(), get()) }
    factory { GetReleasesMovies(get()) }
    single { RoomDataSource(get()) }

    factory { (view: FavouritePresenter.View) -> FavouritePresenter(get(), get()) }
    factory { GetFavouritesMovies(get()) }
}