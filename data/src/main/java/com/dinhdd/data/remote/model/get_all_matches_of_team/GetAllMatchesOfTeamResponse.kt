package com.dinhdd.data.remote.model.get_all_matches_of_team


import com.google.gson.annotations.SerializedName

data class GetAllMatchesOfTeamResponse(
    @SerializedName("matches")
    val matches: Matches? = null
)