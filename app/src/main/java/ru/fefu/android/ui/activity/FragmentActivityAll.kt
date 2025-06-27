package ru.fefu.android.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.android.R
import ru.fefu.android.ui.activity.details.FragmentAllDetails

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

    override fun getDetails(): Fragment {
        return FragmentAllDetails()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentActivityAll()
    }
}
