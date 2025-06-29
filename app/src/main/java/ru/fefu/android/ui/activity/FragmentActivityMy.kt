package ru.fefu.android.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.fefu.android.R
import ru.fefu.android.ui.activity.models.ListItemUIModel

class FragmentActivityMy : ActivityFragmentRecycler() {
    override fun updateRecyclerView(listItemsAdapter: ListItemsAdapter) {
        val recyclerList = viewModel.getRecyclerList { it.data._email == "nickname" }
        listItemsAdapter.setData(recyclerList.map { activity ->
            if (activity is ListItemUIModel.Activity) ListItemUIModel.Activity(
                activity.data.copy(_email = "")
            ) else activity
        })
    }

    override fun detailsNavigation(): Int {
        return R.id.action_navigation_activity_to_navigation_my_details
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_activity_my, container, false)
        createRecycler(view, inflater)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentActivityMy()
    }
}