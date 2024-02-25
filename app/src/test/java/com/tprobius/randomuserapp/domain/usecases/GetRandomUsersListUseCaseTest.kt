package com.tprobius.randomuserapp.domain.usecases

import com.tprobius.randomuserapp.data.repository.FakeRandomUserApiRepositoryImpl
import com.tprobius.randomuserapp.data.repository.FakeRandomUserDatabaseRepositoryImpl
import com.tprobius.randomuserapp.domain.entities.RandomUser
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetRandomUsersListUseCaseTest {

    private val getRandomUsersListUseCase = GetRandomUsersListUseCase(
        FakeRandomUserApiRepositoryImpl(),
        FakeRandomUserDatabaseRepositoryImpl()
    )

    @Test
    fun `use case returns expected users list from api repository`() = runTest {
        val expected = listOf(
            RandomUser(
                gender = "Female",
                first = "Sansa",
                last = "Stark",
                city = "Winterfell",
                state = " the North"
            )
        )

        val actual = getRandomUsersListUseCase.invoke(true)

        assertEquals(expected, actual)
    }

    @Test
    fun `use case returns expected users list from database repository`() = runTest {
        val expected = listOf(

            RandomUser(
                gender = "Male",
                first = "Jon",
                last = "Snow",
                city = "Castle Black",
                state = "The Wall"
            )
        )

        val actual = getRandomUsersListUseCase.invoke(false)

        assertEquals(expected, actual)
    }
}