package info.javaway.enotty.backend.validation.cor

/**
 * Конвейер валидаторов.
 */
class PipelineValidation<C>(
        private val validations: List<IValidationOperation<C, *>>
) {
    fun exec(context: C){
        validations.forEach { it.exec(context) }
    }
}