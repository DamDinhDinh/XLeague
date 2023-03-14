package com.dinhdd.data.remote.model.get_all_matches_of_team


import com.google.gson.annotations.SerializedName

data class UpcomingJson(
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("home")
    val home: String? = null,
    @SerializedName("away")
    val away: String? = null
)