package org.chelak.ea.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import org.chelak.ea.common.Logger
import java.util.concurrent.atomic.AtomicLong


object AlertBundleKeys {
    internal const val id = "alert_id"
    internal const val title = "alert_title"
    internal const val message = "alert_message"
    internal const val positiveButton = "positive_button"
    internal const val negativeButton = "negative_button"
    internal const val neutralButton = "neutral_button"
    internal const val topInitialValue = "alert_initial_value"
    internal const val bottomInitialValue = "alert_2nd_initial_value"
    internal const val topLabel = "alert_top_label"
    internal const val bottomLabel = "alert_bottom_label"
}

var Bundle.alertId: Long
    get() = getLong(AlertBundleKeys.id, 0)
    set(value) {
        putLong(AlertBundleKeys.id, value)
    }

var Bundle.alertTitle: String?
    get() = getString(AlertBundleKeys.title)
    set(value) {
        putString(AlertBundleKeys.title, value)
    }

var Bundle.alertMessage: String?
    get() = getString(AlertBundleKeys.message)
    set(value) {
        putString(AlertBundleKeys.message, value)
    }

var Bundle.alertPositiveButton: String?
    get() = getString(AlertBundleKeys.positiveButton)
    set(value) {
        putString(AlertBundleKeys.positiveButton, value)
    }

var Bundle.alertNegativeButton: String?
    get() = getString(AlertBundleKeys.negativeButton)
    set(value) {
        putString(AlertBundleKeys.negativeButton, value)
    }

var Bundle.alertNeutralButton: String?
    get() = getString(AlertBundleKeys.neutralButton)
    set(value) {
        putString(AlertBundleKeys.neutralButton, value)
    }

var Bundle.alertInitialTextValue: String?
    get() = getString(AlertBundleKeys.topInitialValue)
    set(value) {
        putString(AlertBundleKeys.topInitialValue, value)
    }

var Bundle.alertInitialSecondTextValue: String?
    get() = getString(AlertBundleKeys.bottomInitialValue)
    set(value) {
        putString(AlertBundleKeys.bottomInitialValue, value)
    }

var Bundle.alertTopLabel: String?
    get() = getString(AlertBundleKeys.topLabel)
    set(value) {
        putString(AlertBundleKeys.topLabel, value)
    }


var Bundle.alertBottomLabel: String?
    get() = getString(AlertBundleKeys.bottomLabel)
    set(value) {
        putString(AlertBundleKeys.bottomLabel, value)
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