package com.dinhdd.domain.data_source

import com.dinhdd.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchDataSource {
    suspend fun getAllMatches(): Flow<List<Match>>

    suspend fun getMatchesOfTeam(teamId: String): Flow<List<Match>>
}