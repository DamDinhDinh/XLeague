package com.dinhdd.data.remote.model.get_all_matches


import com.google.gson.annotations.SerializedName

data class GetAllMatchesResponse(
    @SerializedName("matches")
    val matchesJson: MatchesJson? = null
)