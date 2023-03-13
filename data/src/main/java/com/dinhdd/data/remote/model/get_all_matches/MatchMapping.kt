package com.dinhdd.data.remote.model.get_all_matches

import com.dinhdd.domain.model.Match

fun Previou?.toDomain() = if (this != null) {
    Match(
        date = date.orEmpty(),
        description = description.orEmpty(),
        homeTeam = home.orEmpty(),
        awayTeam = away.orEmpty(),
        winner = winner.orEmpty(),
        highlightsUrl = highlights.orEmpty(),
        matchType = Match.MatchType.Previous
    )
} else Match(
    date = "",
    description = "",
    homeTeam = "",
    awayTeam = "",
    winner = "",
    highlightsUrl = "",
    matchType = Match.MatchType.Unknown
)

fun Upcoming?.toDomain() = if (this != null) Match(
    date = date.orEmpty(),
    description = description.orEmpty(),
    homeTeam = home.orEmpty(),
    awayTeam = away.orEmpty(),
    winner = "",
    highlightsUrl = "",
    matchType = Match.MatchType.UpComing
) else Match(
    date = "",
    description = "",
    homeTeam = "",
    awayTeam = "",
    winner = "",
    highlightsUrl = "",
    matchType = Match.MatchType.Unknown
)