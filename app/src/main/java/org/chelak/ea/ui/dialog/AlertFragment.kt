package org.chelak.ea.ui.dialog

import android.content.DialogInterface
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity

typealias DialogAction = () -> Unit

class DialogAlert {

    val alertModel = AlertModel()
    var positiveHandler: DialogAction? = null
        private set
    var negativeHandler: DialogAction? = null
        private set
    var neutralHandler: DialogAction? = null
        private set

    fun setTitle(title: String): DialogAlert {
        alertModel.title = title
        return this
    }

    fun setMessage(message: String): DialogAlert {
        alertModel.message = message
        return this
    }

    fun setPositive(title: String, handler: DialogAction?): DialogAlert {
        alertModel.positiveButton = title
        positiveHandler = handler
        return this
    }

    fun setNegative(title: String, handler: DialogAction? = null): DialogAlert {
        alertModel.negativeButton = title
        negativeHandler = handler
        return this
    }

    fun setNeutral(title: String, handler: DialogAction? = null): DialogAlert {
        alertModel.neutralButton = title
        neutralHandler = handler
        return this
    }

}

class AlertViewModel : GenericActionShareViewModel<DialogAlert>() {

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


fun Fragment.present(alert: DialogAlert) {
    val navController = (activity as? MainActivity)?.navController
    navController?.let {
        val owner = it.getViewModelStoreOwner(R.id.root_nav_graph)
        val viewModel = ViewModelProvider(owner).get(AlertViewModel::class.java)
        val alertId = viewModel.put(alert)
        val model = alert.alertModel
        model.alertId = alertId
        navController.navigate(R.id.alertDialog, model.bundle)
    }
}