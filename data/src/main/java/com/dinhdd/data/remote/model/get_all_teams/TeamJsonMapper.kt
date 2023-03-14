package com.dinhdd.data.remote.model.get_all_teams

fun TeamJson?.toDomain() = if (this != null) {
    com.dinhdd.domain.model.Team(
        id = id.orEmpty(),
        name = name.orEmpty(),
        logoUrl = logo.orEmpty()
    )
} else {
    com.dinhdd.domain.model.Team(
        id = "",
        name = "",
        logoUrl = ""
    )
}
