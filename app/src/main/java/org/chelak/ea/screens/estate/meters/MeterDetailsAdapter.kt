package org.chelak.ea.screens.estate.meters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.database.entity.MeterValue

class MeterDetailsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val COLLAPSED = 1
        const val EXPANDED = 2
    }
    private var values: List<MeterValue> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == COLLAPSED) {
            CollapsedMeterViewHolder.instance(parent)
        } else {
            ExpandedMeterViewHolder.instance(parent)
        }

    override fun getItemCount(): Int = 1 + values.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, postion: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return EXPANDED
        }
        return COLLAPSED
    }

    public fun setValues(values: List<MeterValue>) {
        this.values = values
        notifyDataSetChanged()
    }

}