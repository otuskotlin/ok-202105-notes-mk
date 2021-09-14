package info.javaway.enotty.backend.common.models

@JvmInline
value class UserUidModel(private val uid: String) {
    companion object {
        val NONE = UserUidModel("")
    }

    fun asString() = uid
}
