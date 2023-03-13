package com.dinhdd.data.remote.repo

import com.dinhdd.data.remote.model.get_all_matches.toDomain
import com.dinhdd.data.remote.model.get_all_matches_of_team.toDomain
import com.dinhdd.data.remote.service.MatchServices
import com.dinhdd.domain.data_source.MatchDataSource
import com.dinhdd.domain.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchRepository @Inject constructor(private val matchServices: MatchServices) : MatchDataSource {

    override suspend fun getAllMatches(): Flow<List<Match>> = flow {
        emit(
            matchServices.getAllMatches().matches?.let { matches ->
                val previousMatches = matches.previous?.map {
                    it.toDomain()
                }.orEmpty()
                val upcomingMatches = matches.upcoming?.map {
                    it.toDomain()
                }.orEmpty()
                previousMatches + upcomingMatches
            }.orEmpty()
        )
    }

    override suspend fun getMatchesOfTeam(teamId: String): Flow<List<Match>> = flow {
        emit(
            matchServices.getAllMatchesOfTeam(teamId).matches?.let { matches ->
                val previousMatches = matches.previous?.map {
                    it.toDomain()
                }.orEmpty()
                val upcomingMatches = matches.upcoming?.map {
                    it.toDomain()
                }.orEmpty()
                previousMatches + upcomingMatches
            }.orEmpty()
        )
    }
}