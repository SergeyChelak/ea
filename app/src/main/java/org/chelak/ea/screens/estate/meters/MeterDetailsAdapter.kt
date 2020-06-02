package org.chelak.ea.screens.estate.meters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.common.Logger

class MeterDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val COLLAPSED = 1
        const val EXPANDED = 2
    }

    private var values: List<MeterValueDisplayModel> = emptyList()
    var saveHandler: ((Long?, MeterValueUserInput) -> Unit)? = null

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

    private fun bindCollapsed(
        viewHolder: CollapsedMeterViewHolder,
        value: MeterValueDisplayModel?
    ) {
        value?.let {
            viewHolder.setValue(it.formattedValue)
            viewHolder.setDate(it.formattedDate)
            viewHolder.setPaid(it.isPaid)
            viewHolder.setStateSwitchHandler {
                // TODO implement
            }
        }
    }

    private fun bindExpanded(viewHolder: ExpandedMeterViewHolder, value: MeterValueDisplayModel?) {
        viewHolder.setIsNewItem(value == null)
        value?.let {
            viewHolder.setStateSwitchHandler {
                // TODO implement
            }
            viewHolder.setDate(value.date)
            viewHolder.setValue(value.formattedValue)
            viewHolder.setPaid(value.isPaid)
        }
        viewHolder.setSaveHandler {
            saveHandler?.invoke(value?.uid, it)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return EXPANDED
        }
        return COLLAPSED
    }

    fun setValues(values: List<MeterValueDisplayModel>) {
        this.values = values
        notifyDataSetChanged()
    }

}