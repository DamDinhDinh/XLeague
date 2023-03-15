package com.dinhdd.xleague.presenter.screen.host_screen

enum class XLeagueDestination {
    Unknown,
    HostScreen,
    HomeScreen,
    TeamListing,
    MatchListing,
    MatchOfTeam,
    MatchHighlight;

    companion object {
        fun fromRoute(route: String?): XLeagueDestination =
            when (route?.substringBefore("/")) {
                TeamListing.name -> TeamListing
                MatchListing.name -> MatchListing
                MatchHighlight.name -> MatchHighlight
                MatchOfTeam.name -> MatchOfTeam
                HomeScreen.name -> HomeScreen
                null -> HostScreen
                else -> Unknown
            }
    }
}