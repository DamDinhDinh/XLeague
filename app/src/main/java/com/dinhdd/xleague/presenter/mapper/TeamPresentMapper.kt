package com.dinhdd.xleague.presenter.mapper

import com.dinhdd.domain.model.Team
import com.dinhdd.xleague.presenter.model.TeamPresent

fun Team.toPresent() = TeamPresent(
    id = id,
    name = name,
    logoUrl = logoUrl
)