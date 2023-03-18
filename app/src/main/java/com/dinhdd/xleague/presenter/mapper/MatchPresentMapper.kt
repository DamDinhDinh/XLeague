package com.dinhdd.xleague.presenter.mapper

import com.dinhdd.domain.model.Match
import com.dinhdd.xleague.presenter.model.MatchPresent
import com.dinhdd.xleague.presenter.util.getFormattedDateOfMatch
import com.dinhdd.xleague.presenter.util.getFormattedTimeOfMatch

fun Match.toPresent() = MatchPresent(
    date = date,
    description = description,
    homeTeam = homeTeam,
    awayTeam = awayTeam,
    winner = winner,
    highlightsUrl = highlightsUrl,
    matchType = matchType.toPresent(),
    formattedDate = date.getFormattedDateOfMatch(),
    formattedTime = date.getFormattedTimeOfMatch()
)

fun Match.MatchType.toPresent() = when (this) {
    Match.MatchType.Unknown -> MatchPresent.MatchType.Unknown
    Match.MatchType.Previous -> MatchPresent.MatchType.Previous
    Match.MatchType.UpComing -> MatchPresent.MatchType.UpComing
}