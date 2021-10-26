package info.javaway.enotty.backend.validation.validators

import info.javaway.enotty.backend.validation.IValidator
import info.javaway.enotty.backend.validation.ValidationFieldError
import info.javaway.enotty.backend.validation.ValidationResult

/**
 * Валидатор, отвечающий за проверку строки на непустое значение.
 *
 * @property field валидируемое поле.
 * @property message сообщение ошибки.
 */
class ValidatorStringNonEmpty(
        private val field: String = "",
        private val message: String = ""
) : IValidator<String?> {
    override fun validate(sample: String?): ValidationResult = if (sample.isNullOrEmpty()) {
        ValidationResult(
                errors = listOf(
                        ValidationFieldError(
                                field = field,
                                message = message
                        )
                )
        )
    } else {
        ValidationResult.SUCCESS
    }
}