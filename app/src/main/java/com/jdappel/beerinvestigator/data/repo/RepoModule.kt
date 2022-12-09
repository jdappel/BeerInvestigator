package com.jdappel.beerinvestigator.data.repo

import dagger.Binds
import dagger.Module

@Module
internal interface RepoModule {
    @Binds
    fun bindRepo(impl: BreweryDBRepoImpl): BreweryDBRepo
}