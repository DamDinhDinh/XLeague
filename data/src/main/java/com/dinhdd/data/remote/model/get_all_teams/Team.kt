package com.dinhdd.data.remote.model.get_all_teams


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("logo")
    val logo: String? = null
)