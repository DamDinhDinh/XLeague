package com.dinhdd.xleague.di

import com.dinhdd.data.remote.repo.MatchRepository
import com.dinhdd.data.remote.repo.TeamRepository
import com.dinhdd.domain.data_source.MatchDataSource
import com.dinhdd.domain.data_source.TeamDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun getTeamDataSource(teamRepository: TeamRepository): TeamDataSource

    @Binds
    abstract fun getMatchDataSource(matchRepository: MatchRepository): MatchDataSource
}