package info.javaway.enotty.backend.validation.cor

import info.javaway.enotty.backend.validation.IValidator
import info.javaway.enotty.backend.validation.ValidationResult

/**
 * DSL билдер операции валидации.
 *
 * @property errorHandler екстеншн контекста, обрабатывающий ошибки.
 * @property onBlock екстеншн контекста, возвращающий валидируемое значение [T].
 * @property validator валидатор значения [T].
 */
class ValidationOperationDSL<C, T>(
        private var errorHandler: C.(ValidationResult) -> Unit = {}
) {
    private lateinit var onBlock: C.() -> T
    private lateinit var validator: IValidator<T>

    fun validator(validator: IValidator<T>){
        this.validator = validator
    }

    fun onValue(block: C.() -> T){
        onBlock = block
    }

    fun build(): IValidationOperation<C, T> = DefaultValidationOperation(
            validator = validator,
            onBlock = onBlock,
            errorHandler = errorHandler
    )
}