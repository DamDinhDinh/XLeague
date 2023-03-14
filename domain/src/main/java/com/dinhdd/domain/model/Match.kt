package com.dinhdd.domain.model

data class Match(
    val date: String,
    val description: String,
    val homeTeam: String,
    val awayTeam: String,
    val winner: String,
    val highlightsUrl: String,
    val matchType: MatchType
) {
    enum class MatchType {
        Unknown,
        Previous,
        UpComing
    }
}
