package org.chelak.ea.screens.estate.meters

import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.chelak.ea.R
import org.chelak.ea.ui.VoidHandler
import org.chelak.ea.ui.list.inflateView
import java.util.*


open class MeterViewHolder(view: View): RecyclerView.ViewHolder(view) {

    protected val stateButton: ImageButton = view.findViewById(R.id.changeStateButton)

    fun setStateSwitchHandler(handler: VoidHandler?) {
        stateButton.setOnClickListener {
            handler?.invoke()
        }
    }

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


    fun setPaid(isPaid: Boolean) {
        accessoryImage.isVisible = !isPaid
    }

    fun setValue(value: String) {
        textValue.text = value
    }

    fun setDate(date: String) {
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

    val editValue: EditText = view.findViewById(R.id.editValue)
    val paidSwitch: Switch = view.findViewById(R.id.switchPaid)
    val calendarView: CalendarView = view.findViewById(R.id.calendarView)
    val saveButton: Button = view.findViewById(R.id.buttonSave)
    val deleteButton: Button = view.findViewById(R.id.buttonDelete)

    fun setIsNewItem(isNew: Boolean) {
        arrayOf(deleteButton, stateButton).forEach {
            it.isVisible = !isNew
        }
    }

    fun setPaid(isPaid: Boolean) {
        paidSwitch.isChecked = isPaid
    }

    fun setValue(value: String) {
        editValue.setText(value)
    }

    fun setDate(date: Date) {
        calendarView.setDate(date.time, false, true)
    }

    fun setSaveHandler(handler: SaveMeterValueHandler?) {

    }

}