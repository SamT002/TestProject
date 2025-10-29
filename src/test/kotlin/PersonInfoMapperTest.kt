import data.model.InputDto
import data.model.PersonInfoDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.example.domain.mapper.PersonInfoMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import kotlin.test.assertEquals

class PersonInfoMapperTest {

    private val mapper = PersonInfoMapper()

    @OptIn(ExperimentalCoroutinesApi::class)
    @JvmField
    @RegisterExtension
    val mainDispatcherRule = MainDispatcherExtension()

    @Test
    fun `fromNetwork should return OutputModel with default values when InputDto has null fields`() = runTest {
        // given
        val testAdult = listOf(
            PersonInfoDto(
                id = 123212,
                name = "testName"
            )
        )
        val testChild = listOf(
            PersonInfoDto(
                id = 123212,
                name = "testName"
            )
        )

        val testData = InputDto(
            id = 10,
            adults = testAdult,
            children = testChild
        )

        // trigger
        val result = mapper.fromNetwork(testData)


        // result
        assertEquals(testData.id, result.id)

        result.adults.first().apply {
            assertEquals(testAdult.first().id, id)
            assertEquals(testAdult.first().name, name)
        }

        result.children.first().apply {
            assertEquals(testChild.first().id, id)
            assertEquals(testChild.first().name, name)
        }
    }
}