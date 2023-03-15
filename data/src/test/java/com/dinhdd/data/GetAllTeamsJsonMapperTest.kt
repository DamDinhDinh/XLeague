package com.dinhdd.data

import com.dinhdd.data.remote.model.get_all_teams.TeamJson
import com.dinhdd.data.remote.model.get_all_teams.toDomain
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GetAllTeamsJsonMapperTest {

    lateinit var faker: Faker

    @Before
    fun setUp() {
        faker = Faker()
    }

    @Test
    fun checkMappingTeamJsonMatchToDomain() {
        val givenTeam: TeamJson = faker.randomProvider.randomClassInstance()

        val resultTeam = givenTeam.toDomain()

        assertEquals(givenTeam.id, resultTeam.id)
        assertEquals(givenTeam.name, resultTeam.name)
        assertEquals(givenTeam.logo, resultTeam.logoUrl)
    }

    @Test
    fun checkMappingNullTeamJsonToDomain() {
        val givenTeam: TeamJson? = null

        val resultTeam = givenTeam.toDomain()

        assertEquals("", resultTeam.id)
        assertEquals("", resultTeam.name)
        assertEquals("", resultTeam.logoUrl)
    }
}