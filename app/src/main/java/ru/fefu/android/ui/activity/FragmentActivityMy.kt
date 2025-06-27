package ru.fefu.android.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.android.R
import ru.fefu.android.ui.activity.details.FragmentMyDetails
import ru.fefu.android.ui.activity.models.ActivityUIModel
import ru.fefu.android.ui.activity.models.ListItemUIModel

class FragmentActivityMy : ActivityFragmentRecycler() {
    override fun updateRecyclerView(listItemsAdapter: ListItemsAdapter) {
        val recyclerList = viewModel.getRecyclerList { it.data._email == "nickname" }
        listItemsAdapter.setData(recyclerList.map { activity ->
            if (activity is ListItemUIModel.Activity) ListItemUIModel.Activity(
                ActivityUIModel(
                    activity.data._length,
                    activity.data._time,
                    activity.data._type,
                    activity.data._date,
                    "",
                    activity.data._comment
                )
            ) else activity
        })
    }

    override fun getDetails(): Fragment {
        return FragmentMyDetails()
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