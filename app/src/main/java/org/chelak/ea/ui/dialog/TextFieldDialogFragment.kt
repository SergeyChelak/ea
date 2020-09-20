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


typealias TextInputAction = (String) -> Unit

data class TextInputActionHolder(
    var positiveHandler: TextInputAction? = null,
    var negativeHandler: DialogAction? = null
)


class TextInputViewModel : GenericActionShareViewModel<TextInputActionHolder>() {

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
    private var dialogView: View? = null

    @SuppressLint("InflateParams")
    override fun setupView(builder: AlertDialog.Builder) {
        dialogView = activity?.layoutInflater?.inflate(R.layout.view_single_text_input, null)
        dialogView?.let {
            val editText: EditText = it.findViewById(R.id.textInputField)
            editText.doAfterTextChanged { text -> viewModel.setInputText(text?.toString() ?: "") }
            arguments?.alertInitialTextValue?.let { value ->
                editText.setText(value)
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

}


fun Fragment.presentTextInput(
    title: String? = null,
    positiveTitle: String? = null,
    positiveAction: TextInputAction? = null,
    negativeTitle: String? = null,
    negativeAction: DialogAction? = null,
    options: Bundle? = null
) {
    val navController = (activity as? MainActivity)?.navController
    navController?.let {
        val owner = it.getViewModelStoreOwner(R.id.root_nav_graph)
        val viewModel = ViewModelProvider(owner).get(TextInputViewModel::class.java)
        val holder = TextInputActionHolder(positiveAction, negativeAction)
        val alertId = viewModel.put(holder)
        val params = Bundle().apply {
            options?.let { bundle ->
                this.putAll(bundle)
            }
            this.alertId = alertId
            this.alertTitle = title
            this.alertPositiveButton = positiveTitle
            this.alertNegativeButton = negativeTitle
        }
        navController.navigate(R.id.textInputDialog, params)
    }
}