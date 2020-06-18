package org.chelak.ea.screens.estate.details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.database.entity.Meter
import org.chelak.ea.ui.VoidHandler

typealias MeterSelectionHandler = (Long) -> Unit

class EstateDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_TITLE = 1
        const val TYPE_PAYMENT = 2
        const val TYPE_METER = 3
    }

    private var title: String? = null
    private var meters: List<Meter> = emptyList()
    private var meterInfo = HashMap<Long, MeterBriefInfo>()

    private var calculateHandler: VoidHandler? = null
    private var meterSelectionHandler: MeterSelectionHandler? = null

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> TYPE_TITLE
            1 -> TYPE_PAYMENT
            else -> TYPE_METER
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE -> EstateViewHolder.instance(parent)
            TYPE_PAYMENT -> LastPaymentViewHolder.instance(parent)
            else -> MeterViewHolder.instance(parent)
        }
    }

    override fun getItemCount(): Int = 2 + meters.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_TITLE -> (holder as? EstateViewHolder)?.let {
                it.setTitle(title)
                it.setButtonHandler(calculateHandler)
                it.setImageId(R.drawable.ic_house)
            }
            TYPE_PAYMENT -> (holder as? LastPaymentViewHolder)?.let {
                // TODO implement
            }
            else -> (holder as? MeterViewHolder)?.let { viewHolder ->
                val meter = meters[position - 2]
                viewHolder.setTitle(meter.title)
                viewHolder.cellClickHandler = {
                    meterSelectionHandler?.invoke(meter.uid)
                }
                meterInfo[meter.uid]?.let {
                    viewHolder.setValue(it.value)
                    viewHolder.setDelta(it.delta)
                    viewHolder.setTrendDirection(it.trendDirection)
                }
            }
        }
    }

    fun setEstate(title: String, handler: VoidHandler?) {
        this.title = title
        this.calculateHandler = handler
        notifyItemChanged(0)
    }

    fun setMeters(meters: List<Meter>, handler: MeterSelectionHandler?) {
        this.meters = meters
        this.meterSelectionHandler = handler
        meterInfo.clear()
        notifyDataSetChanged()
    }

    fun updateMeterInfo(info: MeterBriefInfo) {
        val index = meters.indexOfFirst { it.uid == info.uid }
        if (index >= 0) {
            meterInfo[info.uid] = info
            notifyItemChanged(2 + index, Unit)
        }
    }

}