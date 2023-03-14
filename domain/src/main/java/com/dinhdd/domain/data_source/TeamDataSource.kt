package com.dinhdd.domain.data_source

import com.dinhdd.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamDataSource {
    suspend fun getAllTeams(): Flow<List<Team>>
}