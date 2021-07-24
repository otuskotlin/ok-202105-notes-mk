import io.kotest.core.spec.style.BehaviorSpec
import java.util.*

class DddTest: BehaviorSpec( {
    val note = FileDto(
            1000.toString(),
            "some content",
            Date().time,
            Date().time
    )
    given("some content"){
        `when`("note equals some content"){
            `then`("note id equals 1000"){

            }
        }

    }
})