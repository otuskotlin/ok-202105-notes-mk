package info.javaway.enotty.backend.common.models

data class PaginatedModel(
    var size: Int = Int.MIN_VALUE,
    var lastId: NoteIdModel = NoteIdModel.NONE,
    var position: PositionModel = PositionModel.NONE,
) {
    enum class PositionModel {
        NONE,
        FIRST,
        MIDDLE,
        LAST;
    }
}
