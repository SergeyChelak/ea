package org.chelak.ea.screens.estate.meters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.common.Logger
import org.chelak.ea.database.entity.MeterValue
import java.util.*

class MeterDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        val meterValue = if (postion == 0) null else values[postion - 1]
        when (viewHolder) {
            is CollapsedMeterViewHolder -> bindCollapsed(viewHolder, meterValue)
            is ExpandedMeterViewHolder -> bindExpanded(viewHolder, meterValue)
            else -> Logger.e(
                "MeterDetailsAdapter: onBindViewHolder: unknown viewholder type %s",
                viewHolder
            )
        }
    }

    private fun bindCollapsed(viewHolder: CollapsedMeterViewHolder, value: MeterValue?) {
        value?.let {
            // TODO implement with formatter
            var meterValue = "--"
            value.value?.let { longValue ->
                meterValue = longValue.toString()
            }
            viewHolder.setValue(meterValue)
            viewHolder.setDate("")
        }
    }

    private fun bindExpanded(viewHolder: ExpandedMeterViewHolder, value: MeterValue?) {
        viewHolder.setIsNewItem(value == null)
        value?.let {
            viewHolder.setStateSwitchHandler {
                // TODO implement
            }
            viewHolder.setDate(value.date ?: Date())
            value.value?.let { value ->
                // TODO implement with formatter
                viewHolder.setValue(value.toString())
            }
            viewHolder.setPaid(value.isPaid)
        }
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