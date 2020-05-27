package org.chelak.ea.screens.estate.meters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.ui.VoidHandler
import org.chelak.ea.ui.list.inflateView


abstract class MeterViewHolder(view: View): RecyclerView.ViewHolder(view) {

    protected val stateButton: ImageButton = view.findViewById(R.id.changeStateButton)

    fun setStateSwitcher(handler: VoidHandler?) {
        if (handler == null) {
            stateButton.visibility = View.GONE
            return
        }
        stateButton.visibility = View.VISIBLE
        stateButton.setOnClickListener {
            handler.invoke()
        }
    }

    abstract fun setPaid(isPaid: Boolean)

    abstract fun setValue(value: String)

    abstract fun setDate(date: String)

}


class CollapsedMeterViewHolder(view: View): MeterViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): CollapsedMeterViewHolder {
            val view = inflateView(parent, R.layout.viewholder_meter_value_collapsed)
            return CollapsedMeterViewHolder(view)
        }
    }

    val textValue: TextView = view.findViewById(R.id.textValue)
    val textDate: TextView = view.findViewById(R.id.textDate)
    val accessoryImage: ImageView = view.findViewById(R.id.accessoryImage)


    override fun setPaid(isPaid: Boolean) {
        accessoryImage.visibility = if (isPaid) View.GONE else View.VISIBLE
    }

    override fun setValue(value: String) {
        textValue.text = value
    }

    override fun setDate(date: String) {
        textDate.text = date
    }

}


class ExpandedMeterViewHolder(view: View): MeterViewHolder(view) {

    companion object {
        fun instance(parent: ViewGroup): ExpandedMeterViewHolder {
            val view = inflateView(parent, R.layout.viewholder_meter_value_expanded)
            return ExpandedMeterViewHolder(view)
        }
    }

    override fun setPaid(isPaid: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setValue(value: String) {
        TODO("Not yet implemented")
    }

    override fun setDate(date: String) {
        TODO("Not yet implemented")
    }

}