package com.dinhdd.data.remote.model.get_all_matches


import com.google.gson.annotations.SerializedName

data class Matches(
    @SerializedName("previous")
    val previous: List<Previou?>? = null,
    @SerializedName("upcoming")
    val upcoming: List<Upcoming?>? = null
)