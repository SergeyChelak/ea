package org.chelak.ea.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import org.chelak.ea.common.Logger
import java.util.concurrent.atomic.AtomicLong


open class AlertData(bundle: Bundle? = null) {

    companion object {
        internal const val ALERT_ID = "alert_id"
        internal const val ALERT_TITLE = "alert_title"
        internal const val ALERT_MESSAGE = "alert_message"
        internal const val ALERT_POSITIVE_BUTTON = "positive_button"
        internal const val ALERT_NEGATIVE_BUTTON = "negative_button"
        internal const val ALERT_NEUTRAL_BUTTON = "neutral_button"
        internal const val ALERT_INITIAL_VALUE = "alert_initial_value"
        internal const val ALERT_2ND_INITIAL_VALUE = "alert_2nd_initial_value"
    }

    val bundle: Bundle = bundle ?: Bundle()


    var alertId: Long
        get() = bundle.getLong(ALERT_ID, 0)
        set(value) {
            bundle.putLong(ALERT_ID, value)
        }

    var title: String?
        get() = bundle.getString(ALERT_TITLE)
        set(value) {
            bundle.putString(ALERT_TITLE, value)
        }

    var message: String?
        get() = bundle.getString(ALERT_MESSAGE)
        set(value) {
            bundle.putString(ALERT_MESSAGE, value)
        }

    var positiveButton: String?
        get() = bundle.getString(ALERT_POSITIVE_BUTTON)
        set(value) {
            bundle.putString(ALERT_POSITIVE_BUTTON, value)
        }

    var negativeButton: String?
        get() = bundle.getString(ALERT_NEGATIVE_BUTTON)
        set(value) {
            bundle.putString(ALERT_NEGATIVE_BUTTON, value)
        }

    var neutralButton: String?
        get() = bundle.getString(ALERT_NEUTRAL_BUTTON)
        set(value) {
            bundle.putString(ALERT_NEUTRAL_BUTTON, value)
        }

    var initialValue: String?
        get() = bundle.getString(ALERT_INITIAL_VALUE)
        set(value) {
            bundle.putString(ALERT_INITIAL_VALUE, value)
        }

    var secondLineInitialValue: String?
        get() = bundle.getString(ALERT_2ND_INITIAL_VALUE)
        set(value) {
            bundle.putString(ALERT_2ND_INITIAL_VALUE, value)
        }
}


enum class ActionType {
    POSITIVE, NEGATIVE, NEUTRAL
}


open class GenericActionShareViewModel<AlertType> : ViewModel() {

    private val alertId = AtomicLong(0)
    private var alerts : MutableMap<Long, AlertType> = mutableMapOf()

    fun put(alert: AlertType): Long {
        val id = alertId.addAndGet(1)
        alerts[id] = alert
        return id
    }

    fun pop(id: Long): AlertType? {
        var alert: AlertType? = null
        alerts[id]?.let {
            alert = it
            alerts.remove(id)
        }
        return alert
    }

}


open class BaseDialogFragment: DialogFragment() {

    protected val alertId: Long
        get() {
            val alertModel = AlertData(arguments)
            return alertModel.alertId
        }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val alertData = AlertData(arguments)
        alertData.title?.let {
            builder.setTitle(it)
        }
        alertData.message?.let {
            builder.setMessage(it)
        }
        alertData.positiveButton?.let {
            builder.setPositiveButton(it, this::onPositiveButtonClick)
        }
        alertData.negativeButton?.let {
            builder.setNegativeButton(it, this::onNegativeButtonClick)
        }
        alertData.neutralButton?.let {
            builder.setNeutralButton(it, this::onNeutralButtonClick)
        }
        setupView(builder)
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    open fun setupView(builder: AlertDialog.Builder) {
        // override in children
    }

    open fun onPositiveButtonClick(dialogInterface: DialogInterface, which: Int) {
        Logger.i("Default positive button handler called")
    }

    open fun onNegativeButtonClick(dialogInterface: DialogInterface, which: Int) {
        Logger.i("Default negative button handler called")
    }

    open fun onNeutralButtonClick(dialogInterface: DialogInterface, which: Int) {
        Logger.i("Default neutral button handler called")
    }

}