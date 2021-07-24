import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JUnit5Test {

    @BeforeAll
    fun tearUp() {
        println("init test")
    }

    @Test
    fun testCreateNote() {
        val note = FileDto(
                UUID.randomUUID().toString(),
                "some content",
                Date().time,
                Date().time
                )

        assertEquals("some content", note.content)
    }

    @AfterAll
    fun clearGarbage() {
        println("finish test")
    }
}