package info.javaway.enotty.backend.logic.chains.helpers

import info.javaway.enotty.backend.common.context.CorStatus
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.common.models.CommonErrorModel
import info.javaway.enotty.backend.cor.ICorChainDsl
import info.javaway.enotty.backend.validation.IValidationFieldError
import info.javaway.enotty.backend.validation.cor.ValidationDSL
import info.javaway.enotty.backend.validation.workers.validation

/**
 * DSL чейн валидации, добавляющий в контекст ошибки, возникшие в процессе валидации,э
 * и переводящий статус пайплайна в [CorStatus.FAILING].
 */
fun ICorChainDsl<EnottyContext>.enottyValidation(block: ValidationDSL<EnottyContext>.() -> Unit) = validation {
    errorHandler { validationResult ->
        if (validationResult.isSuccess) return@errorHandler
        val errs = validationResult.errors.map {
            CommonErrorModel(
                    message = it.message,
                    field = if (it is IValidationFieldError) it.field else ""
            )
        }
        errors.addAll(errs)
        status = CorStatus.FAILING
    }
    block()
}