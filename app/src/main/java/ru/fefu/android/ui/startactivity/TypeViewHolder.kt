package ru.fefu.android.ui.startactivity

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.TextView
import ru.fefu.android.R
import ru.fefu.android.ui.ListItemViewHolder
import ru.fefu.android.ui.activity.models.ListItemUIModel

class TypeViewHolder(
    private val containerView: View,
    private val onClickListener: ListItemOnClickListener,
) : ListItemViewHolder(containerView) {
    private val activityTypeView: TextView by lazy {
        containerView.findViewById(R.id.activity_type)
    }
    var idx = 0

    private fun changeStroke(view: View?, color: Int) {
        if (view == null) return
        val border = if (view.background is GradientDrawable) {
            view.background as GradientDrawable
        } else {
            GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
            }
        }

        border.setStroke(
            2, color
        )
        view.background = border
    }

    override fun bindData(listItem: ListItemUIModel) {
        require(listItem is ListItemUIModel.Type) {
            "Expected ListItemUIModel.Type"
        }
        activityTypeView.text = listItem.type
        changeStroke(containerView, listItem.color)
        containerView.setOnClickListener {
            onClickListener.oniItemClick(idx)
        }
    }
}