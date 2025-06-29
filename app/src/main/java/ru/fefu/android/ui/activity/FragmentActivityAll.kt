package ru.fefu.android.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.fefu.android.R

class FragmentActivityAll : ActivityFragmentRecycler() {
    override fun updateRecyclerView(listItemsAdapter: ListItemsAdapter) {
        val recyclerList = viewModel.getRecyclerList { true }
        listItemsAdapter.setData(recyclerList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_activity_all, container, false)
        createRecycler(view, inflater)
        return view
    }

    override fun detailsNavigation(): Int {
        return R.id.action_navigation_activity_to_navigation_all_details
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentActivityAll()
    }
}
