package com.jdappel.beerinvestigator.data.repo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepoModule {
    @Binds
    fun bindRepo(impl: BreweryDBRepoImpl): BreweryDBRepo
}