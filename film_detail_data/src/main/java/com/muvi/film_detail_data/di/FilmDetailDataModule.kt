package com.muvi.film_detail_data.di

import com.muvi.film_detail.domain.FilmDetailRepository
import com.muvi.film_detail_data.AndroidFilmDetailRepository
import com.muvi.film_detail_remote.FilmDetailRemote
import com.muvi.film_detail_remote.FilmDetailRemoteModule
import dagger.Module
import dagger.Provides

@Module(includes = [
    FilmDetailRemoteModule::class
])
object FilmDetailDataModule {

    @JvmStatic
    @Provides
    fun filmRepository(filmDetailRemote: FilmDetailRemote): FilmDetailRepository {
        return AndroidFilmDetailRepository(filmDetailRemote)
    }
}