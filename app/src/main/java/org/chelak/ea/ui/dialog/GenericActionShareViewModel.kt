package org.chelak.ea.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import org.chelak.ea.common.Logger
import java.util.concurrent.atomic.AtomicLong

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

    protected lateinit var alertModel: AlertModel
    protected val alertId: Long get() = alertModel.alertId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alertModel = AlertModel(arguments)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        alertModel.title?.let {
            builder.setTitle(it)
        }
        alertModel.message?.let {
            builder.setMessage(it)
        }
        alertModel.positiveButton?.let {
            builder.setPositiveButton(it, this::onPositiveButtonClick)
        }
        alertModel.negativeButton?.let {
            builder.setNegativeButton(it, this::onNegativeButtonClick)
        }
        alertModel.neutralButton?.let {
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