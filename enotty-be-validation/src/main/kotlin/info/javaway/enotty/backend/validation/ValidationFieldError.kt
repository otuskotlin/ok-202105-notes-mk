package info.javaway.enotty.backend.validation

/**
 * Класс ошибки валидации поля.
 */
data class ValidationFieldError(
        override val message: String,
        override val field: String
): IValidationError, IValidationFieldError
