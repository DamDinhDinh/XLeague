package com.dinhdd.data.remote.model.get_all_teams


import com.google.gson.annotations.SerializedName

data class GetAllTeamsResponse(
    @SerializedName("teams")
    val teamJsons: List<TeamJson?>? = null
)