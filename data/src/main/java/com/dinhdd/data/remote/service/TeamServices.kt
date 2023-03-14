package com.dinhdd.data.remote.service

import com.dinhdd.data.remote.model.get_all_teams.GetAllTeamsResponse
import retrofit2.http.GET

interface TeamServices {
    @GET("/teams")
    suspend fun getAllTeams(): GetAllTeamsResponse
}