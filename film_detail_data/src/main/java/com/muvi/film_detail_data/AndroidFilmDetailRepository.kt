package com.muvi.film_detail_data

import com.muvi.base_domain.Film
import com.muvi.film_detail.domain.FilmDetailRepository
import com.muvi.film_detail_remote.FilmDetailRemote

class AndroidFilmDetailRepository internal constructor(
    private val filmDetailRemote: FilmDetailRemote
) : FilmDetailRepository {

    override suspend fun getFilm(id: String): Film {
        return filmDetailRemote.getFilm(id)
    }
}
