import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import java.util.*

class KotestTestJvm: StringSpec( {
    val note = FileDto(
            1000.toString(),
            "some content",
            Date().time,
            Date().time
    )

    "first test Jvm" {
        note.id shouldBe 1000.toString()
    }

    "maximum of two numbers" {
        forAll(
                row(1, 5, 5),
                row(1, 0, 1),
                row(0, 0, 0)
        ) { a, b, max ->
            Math.max(a, b) shouldBe max
        }
    }
})