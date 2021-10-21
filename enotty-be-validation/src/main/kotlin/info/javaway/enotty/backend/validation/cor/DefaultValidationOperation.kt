package info.javaway.enotty.backend.validation.cor

import info.javaway.enotty.backend.validation.IValidator
import info.javaway.enotty.backend.validation.ValidationResult

/**
 * Общий кейс валидации.
 *
 * @property onBlock экстеншн контекста, который возвращает проверяемый параметр.
 * @property validator валидатор параметра T.
 * @property errorHandler экстеншн контекста, обрабатывающий ошибки, обёрнутые в [ValidationResult]
 */
class DefaultValidationOperation<C, T>(
        private val onBlock: C.() -> T,
        private val validator: IValidator<T>,
        private val errorHandler: C.(ValidationResult) -> Unit = {}
): IValidationOperation<C, T> {
    override fun exec(context: C) {
        val value = context.onBlock()
        val res = validator.validate(value)
        context.errorHandler(res)
    }
}