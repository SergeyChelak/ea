package org.chelak.ea.ui.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import org.chelak.ea.R
import org.chelak.ea.common.Logger
import org.chelak.ea.ui.MainActivity


class TextInputAlertModel(bundle: Bundle? = null) : AlertModel(bundle) {

    companion object {
        internal const val ALERT_INITIAL_VALUE = "alert_initial_value"
    }

    var initialValue: String?
        get() = bundle.getString(ALERT_INITIAL_VALUE)
        set(value) {
            bundle.putString(ALERT_INITIAL_VALUE, value)
        }
}

typealias TextInputAction = (String?) -> Unit

class TextInputAlert {

    val alertModel = TextInputAlertModel()

    var positiveHandler: TextInputAction? = null
        private set

    var negativeHandler: DialogAction? = null
        private set

    fun setTitle(title: String): TextInputAlert {
        alertModel.title = title
        return this
    }

    fun setMessage(message: String): TextInputAlert {
        alertModel.message = message
        return this
    }

    fun setInitialValue(value: String): TextInputAlert {
        alertModel.initialValue = value
        return this
    }

    fun setPositive(title: String, handler: TextInputAction?): TextInputAlert {
        alertModel.positiveButton = title
        positiveHandler = handler
        return this
    }

    fun setNegative(title: String, handler: DialogAction? = null): TextInputAlert {
        alertModel.negativeButton = title
        negativeHandler = handler
        return this
    }

}


class TextInputViewModel : GenericActionShareViewModel<TextInputAlert>() {

    private var inputText: String = ""

    fun setInputText(text: String) {
        inputText = text
    }

    fun handle(id: Long, actionType: ActionType) {
        pop(id)?.let {
            when (actionType) {
                ActionType.POSITIVE -> it.positiveHandler?.invoke(inputText)
                ActionType.NEGATIVE -> it.negativeHandler?.invoke()
                else -> {
                    Logger.w("Unexpected action $actionType)")
                }
            }
        }
    }

}


class TextInputDialogFragment : BaseDialogFragment() {

    private val viewModel: TextInputViewModel by navGraphViewModels(R.id.root_nav_graph)
    var dialogView: View? = null

    @SuppressLint("InflateParams")
    override fun setupView(builder: AlertDialog.Builder) {
        dialogView = activity?.layoutInflater?.inflate(R.layout.view_single_text_input, null)
        dialogView?.let {
            val editText: EditText = it.findViewById(R.id.textInputField)
            editText.doAfterTextChanged { text -> viewModel.setInputText(text?.toString() ?: "") }
            val model = TextInputAlertModel(arguments)
            model.initialValue?.let {
                editText.setText(it)
            }
            builder.setView(it)
        }
    }

    override fun onPositiveButtonClick(dialogInterface: DialogInterface, which: Int) {
        viewModel.handle(alertId, ActionType.POSITIVE)
    }

    override fun onNegativeButtonClick(dialogInterface: DialogInterface, which: Int) {
        viewModel.handle(alertId, ActionType.NEGATIVE)
    }

    override fun onNeutralButtonClick(dialogInterface: DialogInterface, which: Int) {
        // neutral not supported
    }

}


fun Fragment.present(alert: TextInputAlert) {
    val navController = (activity as? MainActivity)?.navController
    navController?.let {
        val owner = it.getViewModelStoreOwner(R.id.root_nav_graph)
        val viewModel = ViewModelProvider(owner).get(TextInputViewModel::class.java)
        val alertId = viewModel.put(alert)
        val model = alert.alertModel
        model.alertId = alertId
        navController.navigate(R.id.textInputDialog, model.bundle)
    }
}