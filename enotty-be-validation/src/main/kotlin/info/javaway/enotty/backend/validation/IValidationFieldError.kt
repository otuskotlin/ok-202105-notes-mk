package info.javaway.enotty.backend.validation

/**
 * Интерфейс ошибки валидации поля.
 *
 * @property field поле, при валидации которого произошла ошибка.
 */
interface IValidationFieldError: IValidationError {
    val field: String
}