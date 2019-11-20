package com.muvi.remote.di

import com.muvi.remote.Clock
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.LetterboxdApiFactory
import dagger.Module
import dagger.Provides
import org.koin.dsl.module
import javax.inject.Singleton

/**
 * Since lots of modules will be using [LetterboxdApi] it makes sense to provide it from one Dagger
 * module.
 *
 * We want to treat the letterboxd_api module as an SDK, so keeping Dagger out of that, else that
 * would have been a perfect location for this module.
 */
@Module
object LetterboxdApiModule {

    @JvmStatic
    @Provides
    @Singleton
    fun letterboxdApi(@HttpDebuggable debuggable: Boolean) = createLetterboxdApi(debuggable)
}

/**
 * Same as above, but for Koin.
 */
val letterboxdApiModule = module(override = true) {

    factory {
        createLetterboxdApi(get())
    }
}

private fun createLetterboxdApi(debuggable: Boolean): LetterboxdApi {
    val letterboxdApiFactory = LetterboxdApiFactory(
        apiKey = BuildConfig.API_KEY,
        apiSecret = BuildConfig.API_SECRET,
        enableHttpLogging = debuggable,
        clock = clock()
    )
    return letterboxdApiFactory.remoteLetterboxdApi()
}

private fun clock(): Clock = object : Clock {
    override fun currentTimeMillis(): Long = System.currentTimeMillis()
}
