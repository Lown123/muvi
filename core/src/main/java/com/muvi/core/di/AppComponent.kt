package com.muvi.core.di

import android.app.Application
import android.content.Context
import com.muvi.core.BuildConfig
import com.muvi.remote.ApiConfig
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.di.LetterboxdApiModule
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import org.koin.dsl.module
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        LetterboxdApiModule::class
    ]
)
@Singleton
interface AppComponent {

    fun context(): Context

    fun letterboxdApi(): LetterboxdApi

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module
internal abstract class AppModule {

    @Binds
    abstract fun context(application: Application): Context

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun config() = buildConfig()
    }
}

/**
 * Same as above, but for Koin.
 */
val appModule = module(override = true) {

    single {
        buildConfig()
    }
}

private fun buildConfig(): ApiConfig {
    return ApiConfig(
        key = BuildConfig.API_KEY,
        secret = BuildConfig.API_SECRET,
        debuggable = BuildConfig.DEBUG
    )
}
