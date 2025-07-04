package ru.fefu.android.ui.startactivity

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import androidx.core.content.ContextCompat
import ru.fefu.android.R
import ru.fefu.android.ui.activity.models.ListItemUIModel
import java.time.LocalDateTime
import kotlin.random.Random

class StartActivityFragment : Fragment() {

    companion object {
        fun newInstance() = StartActivityFragment()
    }

    private val viewModel by activityViewModels<StartActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_start_activity, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_view)
        val listItemsAdapter by lazy {
            TypeRecyclerAdapter(inflater, object : ListItemOnClickListener {
                override fun oniItemClick(position: Int) {
                    viewModel.typeId.value?.let {
                        (recyclerView.adapter as TypeRecyclerAdapter).updateData(
                            it,
                            requireContext().getColor(R.color.line)
                        )
                    }
                    viewModel.typeId.value?.let {
                        (recyclerView.adapter as TypeRecyclerAdapter).updateData(
                            position,
                            requireContext().getColor(R.color.blue)
                        )
                    }
                    viewModel.typeId.postValue(position)
                }
            })
        }
        recyclerView.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect, view: View, parent: RecyclerView,
                state: RecyclerView.State,
            ) {
                val position = parent.getChildAdapterPosition(view)
                if (position != NO_POSITION && position != 0) {
                    outRect.left = 10
                }

            }
        })
        recyclerView.adapter = listItemsAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        listItemsAdapter.setData((0..<viewModel.typeList.size).map {
            ListItemUIModel.Type(
                viewModel.formattedType(requireContext(), it),
                if (it == viewModel.typeId.value) {
                    ContextCompat.getColor(requireContext(), R.color.blue)
                } else {
                    ContextCompat.getColor(requireContext(), R.color.line)
                }
            )
        })

        view.findViewById<Button>(R.id.start_activity).setOnClickListener {
            viewModel.apply {
                length.postValue(Random.nextInt(1,5000))
                start.postValue(LocalDateTime.now())
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.menu_fragment, DoingActivityFragment.newInstance()).commitNow()
        }
        return view
    }

}