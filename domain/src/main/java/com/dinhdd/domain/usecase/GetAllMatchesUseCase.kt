package com.dinhdd.domain.usecase

import com.dinhdd.domain.data_source.MatchDataSource
import com.dinhdd.domain.model.Match
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMatchesUseCase @Inject constructor(private val matchDataSource: MatchDataSource) {
    suspend operator fun invoke(): Flow<List<Match>> {
        return matchDataSource.getAllMatches()
    }
}