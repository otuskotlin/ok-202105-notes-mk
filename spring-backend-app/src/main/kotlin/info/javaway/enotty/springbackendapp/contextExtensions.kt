package info.javaway.enotty.springbackendapp

import info.javaway.enotty.backend.common.context.CorStatus
import info.javaway.enotty.backend.common.context.MpContext
import info.javaway.enotty.backend.common.models.CommonErrorModel
import info.javaway.enotty.backend.common.models.IError

fun MpContext.addError(lambda: CommonErrorModel.() -> Unit) =
        apply {
            status = CorStatus.FAILING
            errors.add(
                    CommonErrorModel(
                            field = "_", level = IError.Level.ERROR
                    ).apply(lambda)
            )
        }