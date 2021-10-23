package info.javaway.enotty.backend.validation.validators

import info.javaway.enotty.backend.validation.IValidator
import info.javaway.enotty.backend.validation.ValidationFieldError
import info.javaway.enotty.backend.validation.ValidationResult

/**
 * Валидатор, отвечающий за проверку вхождения поля в диапазон значений [T]
 *
 * @property field валидируемое поле.
 * @property min минимальное значение валидации.
 * @property max максимальное значение валидации.
 */
class ValidatorRange<T : Comparable<T>>(
        private val field: String,
        private val min: T,
        private val max: T
) : IValidator<T> {
    override fun validate(sample: T): ValidationResult = if (sample in min..max) ValidationResult.SUCCESS
    else {
        ValidationResult(
                errors = listOf(
                        ValidationFieldError(
                                message = "Value $sample for field $field exceeds range [$min..$max]",
                                field = field
                        )
                )
        )
    }
}