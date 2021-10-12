package info.javaway.enotty.backend.common.models

import jdk.jfr.StackTrace


interface IError {
    val field: String
    val level: Level
    val message: String
    val exception: Throwable

    enum class Level {
        ERROR,
        WARNING
    }
}
