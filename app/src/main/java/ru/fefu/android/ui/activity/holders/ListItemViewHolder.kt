package ru.fefu.android.ui.activity.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.android.ui.activity.models.ListItemUIModel

abstract class ListItemViewHolder(private val containerView: View) :
    RecyclerView.ViewHolder(containerView) {
    abstract fun bindData(listItem : ListItemUIModel)
}