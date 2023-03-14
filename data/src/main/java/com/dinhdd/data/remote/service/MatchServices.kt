package com.dinhdd.data.remote.service

import com.dinhdd.data.remote.model.get_all_matches.GetAllMatchesResponse
import com.dinhdd.data.remote.model.get_all_matches_of_team.GetAllMatchesOfTeamResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchServices {
    @GET("/teams/matches")
    suspend fun getAllMatches(): GetAllMatchesResponse

    @GET("/teams/{teamId}/matches")
    suspend fun getAllMatchesOfTeam(@Path("teamId") teamId: String): GetAllMatchesOfTeamResponse
}