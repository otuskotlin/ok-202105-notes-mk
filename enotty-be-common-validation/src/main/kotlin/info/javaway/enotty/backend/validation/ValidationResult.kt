package info.javaway.enotty.backend.validation

/**
 * Класс представляющий результат валидации.
 *
 * @property errors список ошибок, которые могут возникнуть при валидации.
 * @property isSuccess результат валидации.
 */
class ValidationResult(val errors: List<IValidationError>) {
    val isSuccess: Boolean
        get() = errors.isEmpty()

    companion object{
        val SUCCESS = ValidationResult(emptyList())
    }
}