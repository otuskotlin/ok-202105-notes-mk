import info.javaway.enotty.backend.cor.chain
import info.javaway.enotty.backend.validation.IValidationError
import info.javaway.enotty.backend.validation.ValidationResult
import info.javaway.enotty.backend.validation.validators.ValidatorStringNonEmpty
import info.javaway.enotty.backend.validation.workers.validation
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class SimpleValidationTest {

    @Test
    fun pipelineValidation() {

        val chain = chain<TestContext> {
            validation {
                errorHandler { v: ValidationResult ->
                    if (!v.isSuccess) {
                        errors.addAll(v.errors)
                    }
                }

                validate<String?> { validator(ValidatorStringNonEmpty()); onValue { x } }
                validate<String?> { validator(ValidatorStringNonEmpty()); onValue { y } }
            }
        }

        val c = TestContext()

        runBlocking {
            chain.build().exec(c)
            assertEquals(2, c.errors.size)
        }

    }

    data class TestContext(
            val x: String = "",
            val y: String = "",
            val errors: MutableList<IValidationError> = mutableListOf()
    )
}