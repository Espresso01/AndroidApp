package ru.fefu.android.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.fefu.android.ui.activity.models.ActivityTypes
import java.time.LocalDateTime

@Entity(tableName = "user_activity")
data class Activity(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val type: ActivityTypes,
    val startDateTime: LocalDateTime,
    val finishDateTime: LocalDateTime,
    val length: Int
)