package info.javaway.enotty.backend.common.models

interface IError {
    var field: String
    var level: Level
    var message: String

    enum class Level {
        ERROR,
        WARNING
    }
}
