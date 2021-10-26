package info.javaway.enotty.backend.validation

/**
 * Общая ошибка валидации.
 */
data class ValidationDefaultError(
        override val message: String
) : IValidationError
