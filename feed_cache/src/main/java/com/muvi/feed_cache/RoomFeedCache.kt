package com.muvi.feed_cache

import com.muvi.base_domain.FilmSummary
import com.muvi.base_domain.Image
import com.muvi.feed_cache.db.DbFilmSummary
import com.muvi.feed_cache.db.DbImage
import com.muvi.feed_cache.db.DbImageSize

internal class RoomFeedCache(
        private val feedDao: FeedDao
) : FeedCache {

    override suspend fun getFeed(): List<FilmSummary> {
        return feedDao.getFilms().map { dbFilmSummary ->
            FilmSummary(
                    dbFilmSummary.id,
                    dbFilmSummary.name,
                    dbFilmSummary.releaseYear,
                    dbFilmSummary.directors,
                    Image(sizes = dbFilmSummary.poster.sizes.map { dbImageSize ->
                        Image.Size(dbImageSize.width, dbImageSize.height, dbImageSize.url)
                    })
            )
        }
    }

    override suspend fun insertFeed(feed: List<FilmSummary>) {
        return feedDao.insertAll(feed.map { filmSummary ->
            DbFilmSummary(
                    filmSummary.id,
                    filmSummary.title,
                    filmSummary.year,
                    filmSummary.directors,
                    DbImage(sizes = filmSummary.poster?.sizes?.map { DbImageSize(it.width, it.height, it.url) }
                            ?: emptyList())
            )
        })
    }
}
