package org.chelak.ea.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.navGraphViewModels
import org.chelak.ea.R
import java.util.concurrent.atomic.AtomicLong
import kotlin.collections.set


class AlertController(private val navController: NavController) {

    companion object {
        internal const val ALERT_ID = "alert_id"
        internal const val ALERT_TITLE = "alert_title"
        internal const val ALERT_MESSAGE = "alert_message"
        internal const val ALERT_POSITIVE_BUTTON = "positive_button"
        internal const val ALERT_NEGATIVE_BUTTON = "negative_button"
        internal const val ALERT_NEUTRAL_BUTTON = "neutral_button"
    }

    internal enum class ActionType {
        POSITIVE, NEGATIVE, NEUTRAL
    }

    fun present(alert: Alert) {
        val owner = navController.getViewModelStoreOwner(R.id.root_nav_graph)
        val viewModel = ViewModelProvider(owner).get(AlertViewModel::class.java)
        val alertId = viewModel.put(alert)
        val params = Bundle()
        params.putLong(ALERT_ID, alertId)
        alert.title?.let {
            params.putString(ALERT_TITLE, it)
        }
        alert.message?.let {
            params.putString(ALERT_MESSAGE, it)
        }
        alert.positiveAction?.title?.let {
            params.putString(ALERT_POSITIVE_BUTTON, it)
        }
        alert.negativeAction?.title?.let {
            params.putString(ALERT_NEGATIVE_BUTTON, it)
        }
        alert.neutralAction?.title?.let {
            params.putString(ALERT_NEUTRAL_BUTTON, it)
        }
        navController.navigate(R.id.alertDialog, params)
    }

    internal class AlertViewModel : ViewModel() {

        private val alertId = AtomicLong(0)
        private var alerts : MutableMap<Long, Alert> = mutableMapOf()

        internal fun put(alert: Alert): Long {
            val id = alertId.addAndGet(1)
            alerts[id] = alert
            return id
        }

        internal fun handle(id: Long, actionType: ActionType) {
            alerts[id]?.let {
                val action: AlertAction? = when (actionType) {
                    ActionType.POSITIVE -> it.positiveAction
                    ActionType.NEGATIVE -> it.negativeAction
                    ActionType.NEUTRAL -> it.neutralAction
                }
                action?.handler?.invoke()
                alerts.remove(id)
            }
        }
    }

    class AlertFragment : DialogFragment() {

        private val viewModel: AlertViewModel by navGraphViewModels(R.id.root_nav_graph)
        private val alertId: Long get() = arguments?.getLong(ALERT_ID) ?: 0

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(context)
            arguments?.let { bundle ->
                bundle.getString(ALERT_TITLE)?.let {
                    builder.setTitle(it)
                }
                bundle.getString(ALERT_MESSAGE)?.let {
                    builder.setMessage(it)
                }
                bundle.getString(ALERT_POSITIVE_BUTTON)?.let {
                    builder.setPositiveButton(it) { _, _ ->
                        viewModel.handle(alertId,
                            ActionType.POSITIVE
                        )
                    }
                }
                bundle.getString(ALERT_NEGATIVE_BUTTON)?.let {
                    builder.setNegativeButton(it) { _, _ ->
                        viewModel.handle(alertId,
                            ActionType.NEGATIVE
                        )
                    }
                }
                bundle.getString(ALERT_NEUTRAL_BUTTON)?.let {
                    builder.setNeutralButton(it) { _, _ ->
                        viewModel.handle(alertId,
                            ActionType.NEUTRAL
                        )
                    }
                }
            }
            val dialog = builder.create()
            dialog.setCanceledOnTouchOutside(false)
            return dialog
        }

    }

}


data class Alert(
    var title: String? = null,
    var message: String? = null) {

    var positiveAction: AlertAction? = null
    var negativeAction: AlertAction? = null
    var neutralAction: AlertAction? = null

}


data class AlertAction(
    val title: String,
    val handler: (() -> Unit)? = null
)