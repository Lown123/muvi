package com.muvi.actor_detail_remote

import org.koin.dsl.module

val actorDetailRemoteModule = module(override = true) {

    factory<ActorDetailRemote> {
        LetterboxdActorDetailRemote(letterboxdApi = get())
    }
}
