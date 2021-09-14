package info.javaway.enotty.backend.common.models

@JvmInline
value class NoteIdModel(private val id: String) {
    companion object{
        val NONE = NoteIdModel("")
    }

    fun asString() = id
}