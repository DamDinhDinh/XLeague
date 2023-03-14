package com.dinhdd.xleague.presenter.model

data class MatchPresent(
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
