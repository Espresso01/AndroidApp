package ru.fefu.android.ui.activity.holders

import android.view.View
import android.widget.TextView
import ru.fefu.android.R
import ru.fefu.android.ui.ListItemViewHolder
import ru.fefu.android.ui.activity.models.ListItemUIModel

class ActivityViewHolder(
    private val containerView: View,
    private val onClickListener: ItemClickListener,
) : ListItemViewHolder(containerView) {
    private val activityLengthView: TextView by lazy { containerView.findViewById(R.id.item_activity_length) }
    private val activityTimeView: TextView by lazy { containerView.findViewById(R.id.item_activity_time) }
    private val activityTypeView: TextView by lazy { containerView.findViewById(R.id.item_activity_type) }
    private val activityDateView: TextView by lazy { containerView.findViewById(R.id.item_activity_date) }
    private val activityEmailView: TextView by lazy { containerView.findViewById(R.id.item_activity_email) }


    override fun bindData(listItem: ListItemUIModel) {
        require(listItem is ListItemUIModel.Activity) {
            "Expected ListItemUIModel.Activity"
        }
        val activityUIModel = listItem.data
        activityLengthView.text = activityUIModel.length
        activityTimeView.text = activityUIModel.time
        activityTypeView.text = activityUIModel.type(containerView.context)
        activityDateView.text = activityUIModel.date
        activityEmailView.text = activityUIModel.email

        containerView.setOnClickListener {
            onClickListener.oniItemClick(activityUIModel)
        }
    }
}