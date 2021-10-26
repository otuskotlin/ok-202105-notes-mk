package info.javaway.enotty.backend.validation.validators

import info.javaway.enotty.backend.validation.IValidator
import info.javaway.enotty.backend.validation.ValidationFieldError
import info.javaway.enotty.backend.validation.ValidationResult
import kotlin.reflect.KClass

/**
 * Валидатор, отвечающий за проверку наличия наличия ошибки валидации поля field
 * в переданном списке ошибки sample.
 *
 * @property field валидируемое поле.
 * @property message сообщение об ошибке.
 * @property klass класс ошибки.
 */
class ValidatorHasException(
        private val field: String = "",
        private val message: String = "List has an exception",
        private val klass: KClass<*> = Any::class
) : IValidator<List<Throwable>> {
    override fun validate(sample: List<Throwable>): ValidationResult =
            if (sample.firstOrNull { it::class == klass } != null) {
                ValidationResult(
                        errors = listOf(
                                ValidationFieldError(
                                        field = field,
                                        message = message
                                )
                        )
                )
            } else ValidationResult.SUCCESS
}