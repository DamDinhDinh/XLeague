package com.dinhdd.data.remote.repo

import com.dinhdd.data.remote.model.get_all_teams.toDomain
import com.dinhdd.data.remote.service.TeamServices
import com.dinhdd.domain.data_source.TeamDataSource
import com.dinhdd.domain.model.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TeamRepository @Inject constructor(private val teamServices: TeamServices) : TeamDataSource {
    override suspend fun getAllTeams(): Flow<List<Team>> = flow {
        emit(
            teamServices.getAllTeams().teamJsons?.map {
                it.toDomain()
            }.orEmpty()
        )
    }
}