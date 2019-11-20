package com.muvi.feed_cache

import com.muvi.base_domain.FilmSummary

interface FeedCache {

    suspend fun getFeed(): List<FilmSummary>

    suspend fun insertFeed(feed: List<FilmSummary>)
}
