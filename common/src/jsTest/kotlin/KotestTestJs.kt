import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.js.Date
import kotlin.random.Random

class KotestTestJs : StringSpec({

  "first test JS" {
      1.toString() shouldBe "1"
  }
})