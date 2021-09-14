package info.javaway.enotty.backend.common.models

data class CommonErrorModel(
    override var field: String = "",
    override var level: IError.Level = IError.Level.ERROR,
    override var message: String = "",
): IError
