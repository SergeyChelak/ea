package org.chelak.ea.ui.dialog

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity

typealias DialogAction = () -> Unit

data class DialogActionHolder(
    var positiveHandler: DialogAction? = null,
    var negativeHandler: DialogAction? = null,
    var neutralHandler: DialogAction? = null
)

class AlertViewModel : GenericActionShareViewModel<DialogActionHolder>() {

    fun handle(id: Long, actionType: ActionType) {
        pop(id)?.let {
            when (actionType) {
                ActionType.POSITIVE -> it.positiveHandler?.invoke()
                ActionType.NEGATIVE -> it.negativeHandler?.invoke()
                ActionType.NEUTRAL -> it.neutralHandler?.invoke()
            }
        }
    }

}


class AlertFragment : BaseDialogFragment() {

    private val viewModel: AlertViewModel by navGraphViewModels(R.id.root_nav_graph)

    override fun onPositiveButtonClick(dialogInterface: DialogInterface, which: Int) {
        viewModel.handle(alertId, ActionType.POSITIVE)
    }

    override fun onNegativeButtonClick(dialogInterface: DialogInterface, which: Int) {
        viewModel.handle(alertId, ActionType.NEGATIVE)
    }

    override fun onNeutralButtonClick(dialogInterface: DialogInterface, which: Int) {
        viewModel.handle(alertId, ActionType.NEUTRAL)
    }
}

data class AlertModel(
    val title: String? = null,
    val message: String? = null,
    val positiveTitle: String? = null,
    val positiveAction: DialogAction? = null,
    val negativeTitle: String? = null,
    val negativeAction: DialogAction? = null,
    val neutralTitle: String? = null,
    val neutralAction: DialogAction? = null
)


fun Fragment.presentAlert(
    title: String? = null,
    message: String? = null,
    positiveTitle: String? = null,
    positiveAction: DialogAction? = null,
    negativeTitle: String? = null,
    negativeAction: DialogAction? = null,
    neutralTitle: String? = null,
    neutralAction: DialogAction? = null
) {
    presentAlert(
        AlertModel(
            title, message, positiveTitle, positiveAction, negativeTitle, negativeAction, neutralTitle, neutralAction
        )
    )
}


fun Fragment.presentAlert(model: AlertModel) {
    val navController = (activity as? MainActivity)?.navController
    navController?.let {
        val owner = it.getViewModelStoreOwner(R.id.root_nav_graph)
        val viewModel = ViewModelProvider(owner).get(AlertViewModel::class.java)
        val holder = DialogActionHolder(model.positiveAction, model.negativeAction, model.neutralAction)
        val alertId = viewModel.put(holder)
        val params = Bundle().apply {
            this.alertId = alertId
            this.alertTitle = model.title
            this.alertMessage = model.message
            this.alertPositiveButton = model.positiveTitle
            this.alertNegativeButton = model.negativeTitle
            this.alertNeutralButton = model.neutralTitle
        }
        navController.navigate(R.id.alertDialog, params)
    }
}

fun Fragment.debugAlert(message: String = "Not implemented") {
    presentAlert(
        title = "Warning",
        message = message,
        positiveTitle = "OK"
    )
}