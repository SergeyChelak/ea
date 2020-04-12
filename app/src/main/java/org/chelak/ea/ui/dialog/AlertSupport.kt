package org.chelak.ea.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import org.chelak.ea.common.Logger
import java.util.concurrent.atomic.AtomicLong


internal class AlertBundleKeys {
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
}

var Bundle.alertId: Long
    get() = getLong(AlertBundleKeys.ALERT_ID, 0)
    set(value) {
        putLong(AlertBundleKeys.ALERT_ID, value)
    }

var Bundle.alertTitle: String?
    get() = getString(AlertBundleKeys.ALERT_TITLE)
    set(value) {
        putString(AlertBundleKeys.ALERT_TITLE, value)
    }

var Bundle.alertMessage: String?
    get() = getString(AlertBundleKeys.ALERT_MESSAGE)
    set(value) {
        putString(AlertBundleKeys.ALERT_MESSAGE, value)
    }

var Bundle.alertPositiveButton: String?
    get() = getString(AlertBundleKeys.ALERT_POSITIVE_BUTTON)
    set(value) {
        putString(AlertBundleKeys.ALERT_POSITIVE_BUTTON, value)
    }

var Bundle.alertNegativeButton: String?
    get() = getString(AlertBundleKeys.ALERT_NEGATIVE_BUTTON)
    set(value) {
        putString(AlertBundleKeys.ALERT_NEGATIVE_BUTTON, value)
    }

var Bundle.alertNeutralButton: String?
    get() = getString(AlertBundleKeys.ALERT_NEUTRAL_BUTTON)
    set(value) {
        putString(AlertBundleKeys.ALERT_NEUTRAL_BUTTON, value)
    }

var Bundle.alertInitialTextValue: String?
    get() = getString(AlertBundleKeys.ALERT_INITIAL_VALUE)
    set(value) {
        putString(AlertBundleKeys.ALERT_INITIAL_VALUE, value)
    }

var Bundle.alertInitialSecondTextValue: String?
    get() = getString(AlertBundleKeys.ALERT_2ND_INITIAL_VALUE)
    set(value) {
        putString(AlertBundleKeys.ALERT_2ND_INITIAL_VALUE, value)
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

    protected val alertId: Long get() = arguments?.alertId ?: 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        arguments?.alertTitle?.let {
            builder.setTitle(it)
        }
        arguments?.alertMessage?.let {
            builder.setMessage(it)
        }
        arguments?.alertPositiveButton?.let {
            builder.setPositiveButton(it, this::onPositiveButtonClick)
        }
        arguments?.alertNegativeButton?.let {
            builder.setNegativeButton(it, this::onNegativeButtonClick)
        }
        arguments?.alertNeutralButton?.let {
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