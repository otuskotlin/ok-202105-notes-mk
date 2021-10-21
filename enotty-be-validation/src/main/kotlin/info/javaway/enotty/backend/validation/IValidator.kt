package info.javaway.enotty.backend.validation

interface IValidator<T> {
    infix fun validate(sample: T): ValidationResult
}