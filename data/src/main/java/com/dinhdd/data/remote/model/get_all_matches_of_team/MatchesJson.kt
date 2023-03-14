package com.dinhdd.data.remote.model.get_all_matches_of_team


import com.google.gson.annotations.SerializedName

data class MatchesJson(
    @SerializedName("previous")
    val previous: List<PreviouJson?>? = null,
    @SerializedName("upcoming")
    val upcomingJson: List<UpcomingJson?>? = null
)