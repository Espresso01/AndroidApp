package ru.fefu.android.ui.activity

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import ru.fefu.android.R
import ru.fefu.android.ui.activity.details.DetailsViewModel
import ru.fefu.android.ui.activity.models.ActivityUIModel
import ru.fefu.android.ui.activity.models.ActivityViewModel


abstract class ActivityFragmentRecycler : Fragment() {
    private val mActivityViewModel by viewModels<ru.fefu.android.data.ActivityViewModel>()
    protected val viewModel by viewModels<ActivityViewModel>()
    protected abstract fun updateRecyclerView(listItemsAdapter: ListItemsAdapter)
    protected fun createRecycler(view : View, inflater : LayoutInflater) : ListItemsAdapter {
        mActivityViewModel.readAllActivities.observe(viewLifecycleOwner){
            viewModel.updateActivities(it)
        }
        val listItemsAdapter by lazy {
            ListItemsAdapter(inflater, object : ListItemActivityOnClickListener {
                override fun oniItemClick(activityUIModel: ActivityUIModel) {
                    goToDetails(activityUIModel)
                }
            })
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_view)
        val spacingPx = (8f * resources.displayMetrics.density).toInt()
        val marginPx = (8f * resources.displayMetrics.density).toInt()

        val dividerItemDecoration = VerticalSpaceItemDecoration(spacingPx, marginPx)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = listItemsAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        viewModel.activities.observe(viewLifecycleOwner){
            updateRecyclerView(listItemsAdapter)
        }
        return listItemsAdapter
    }

    abstract fun detailsNavigation() : Int

    protected fun goToDetails(activityUIModel: ActivityUIModel) {
        val detailsModel by activityViewModels<DetailsViewModel>()
        detailsModel.activity.value = activityUIModel
        detailsModel.id.postValue(activityUIModel.id)
        findNavController().navigate(detailsNavigation())
    }
}