package ru.fefu.android.ui.startactivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.android.R
import ru.fefu.android.ui.ListItemViewHolder
import ru.fefu.android.ui.activity.models.ListItemUIModel

class TypeRecyclerAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClickListener: ListItemOnClickListener,
) : RecyclerView.Adapter<ListItemViewHolder>() {
    private val dataList = mutableListOf<ListItemUIModel>()

    fun updateData(idx : Int, color: Int) {
        (dataList[idx] as ListItemUIModel.Type).color = color
        notifyItemChanged(idx)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newDataList: List<ListItemUIModel>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = layoutInflater.inflate(R.layout.item_type, parent, false)
        return TypeViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        (holder as? TypeViewHolder).let {
            it?.idx = position
        }
        holder.bindData(dataList[position])
    }
}