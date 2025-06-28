package ru.fefu.android.ui.activity.holders

import ru.fefu.android.ui.activity.models.ActivityUIModel

interface ItemClickListener {
    fun oniItemClick(activityUIModel: ActivityUIModel)
}