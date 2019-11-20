package com.muvi.feed_data.di

import com.muvi.feed_cache.FeedCache
import com.muvi.feed_cache.di.FeedCacheModule
import com.muvi.feed_data.CacheAndRemoteFilmRepository
import com.muvi.feed_domain.FilmRepository
import com.muvi.feed_remote.FeedRemote
import com.muvi.feed_remote.di.FeedRemoteModule
import dagger.Module
import dagger.Provides

@Module(includes = [
    FeedCacheModule::class,
    FeedRemoteModule::class]
)
object FeedDataModule {

    @JvmStatic
    @Provides
    fun filmRepository(feedRemote: FeedRemote, feedCache: FeedCache): FilmRepository {
        return CacheAndRemoteFilmRepository(feedRemote, feedCache)
    }
}
