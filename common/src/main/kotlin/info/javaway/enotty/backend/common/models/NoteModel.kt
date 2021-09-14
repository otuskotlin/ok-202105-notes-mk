package info.javaway.enotty.backend.common.models

import java.util.*

data class NoteModel(
        var id: NoteIdModel = NoteIdModel.NONE,
        var title: String = "",
        var content: String = "",
        var parentId: NoteIdModel = NoteIdModel.NONE,
        var role: Role = Role.NONE,
        var color: Int = 0,
        var extendedMode: Boolean = false,
        var icon: String = "",
        var isHidden: Boolean = false,
        var isFavorite: Boolean = false,
        var isShowTitle: Boolean = false,
        var createdAt: Date = Date(),
        var updatedAt: Date = Date(),
        var userUid: UserUidModel = UserUidModel.NONE,
        var permissions: MutableSet<PermissionModel> = mutableSetOf()
)