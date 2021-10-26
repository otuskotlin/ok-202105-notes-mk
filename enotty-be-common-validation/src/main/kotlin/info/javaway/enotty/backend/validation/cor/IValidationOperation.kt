package info.javaway.enotty.backend.validation.cor

/**
 * Интерфейс для представления операции валидации.
 * @property C контекст выполнения валидации.
 * @property T тип валидируемого параметра.
 */
interface IValidationOperation<C, T> {
    fun exec(context: C)
}