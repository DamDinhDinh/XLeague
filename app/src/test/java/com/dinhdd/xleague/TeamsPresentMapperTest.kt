package com.dinhdd.xleague

import com.dinhdd.domain.model.Team
import com.dinhdd.xleague.presenter.mapper.toPresent
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class TeamsPresentMapperTest {

    lateinit var faker: Faker

    @Before
    fun setUp() {
        faker = Faker()
    }

    @Test
    fun checkMappingTeamDomainToPresent() {
        val givenTeam: Team = faker.randomProvider.randomClassInstance()

        val resultTeam = givenTeam.toPresent()

        assertEquals(givenTeam.id, resultTeam.id)
        assertEquals(givenTeam.name, resultTeam.name)
        assertEquals(givenTeam.logoUrl, resultTeam.logoUrl)
    }
}