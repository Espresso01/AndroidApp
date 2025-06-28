package ru.fefu.android.ui.activity

import ru.fefu.android.ui.activity.models.ActivityUIModel

interface ListItemActivityOnClickListener {
    fun oniItemClick(activityUIModel: ActivityUIModel)
}