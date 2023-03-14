package com.dinhdd.domain.usecase

import com.dinhdd.domain.data_source.TeamDataSource
import com.dinhdd.domain.model.Team
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTeamsUseCase @Inject constructor(private val teamDataSource: TeamDataSource) {
    suspend operator fun invoke(): Flow<List<Team>> {
        return teamDataSource.getAllTeams()
    }
}