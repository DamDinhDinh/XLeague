package com.dinhdd.domain.usecase

import com.dinhdd.domain.data_source.MatchDataSource
import com.dinhdd.domain.model.Match
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMatchesOfTeamUseCase @Inject constructor(private val matchDataSource: MatchDataSource) {
    suspend operator fun invoke(teamId: String): Flow<List<Match>> {
        return matchDataSource.getMatchesOfTeam(teamId)
    }
}