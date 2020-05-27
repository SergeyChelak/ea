package org.chelak.ea.screens.estate.meters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.ui.list.inflateView


class CollapsedMeterViewHolder(view: View): RecyclerView.ViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): CollapsedMeterViewHolder {
            val view = inflateView(parent, R.layout.viewholder_meter_value_collapsed)
            return CollapsedMeterViewHolder(view)
        }
    }

}


class ExpandedMeterViewHolder(view: View): RecyclerView.ViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): ExpandedMeterViewHolder {
            val view = inflateView(parent, R.layout.viewholder_meter_value_expanded)
            return ExpandedMeterViewHolder(view)
        }
    }

}