package com.muvi.feed_remote

import com.muvi.base_domain.FilmSummary

interface FeedRemote {

    suspend fun getFeed(): List<FilmSummary>
}
