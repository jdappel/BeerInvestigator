package com.jdappel.beerinvestigator.repo

import dagger.Binds
import dagger.Module

@Module
internal interface RepoModule {
    @Binds
    fun bindRepo(impl: BreweryDBRepoImpl): BreweryDBRepo
}