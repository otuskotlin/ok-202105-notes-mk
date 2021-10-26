package info.javaway.enotty.backend.validation.cor

import info.javaway.enotty.backend.validation.ValidationResult

/**
 * DSL билдер [PipelineValidation].
 */
class ValidationDSL<C> {
    private var errorHandler: C.(ValidationResult) -> Unit = {}
    private val validators: MutableList<IValidationOperation<C, *>> = mutableListOf()

    fun errorHandler(block: C.(ValidationResult) -> Unit){
        errorHandler = block
    }

    fun <T> validate(block: ValidationOperationDSL<C,T>.() -> Unit){
        val dslBuilder = ValidationOperationDSL<C, T>(errorHandler).apply(block)
        validators.add(dslBuilder.build())
    }

    fun build() = PipelineValidation(validators)
}