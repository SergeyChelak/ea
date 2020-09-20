package org.chelak.ea.ui.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import org.chelak.ea.R
import org.chelak.ea.ui.MainActivity

typealias TwoTextInputAction = (String, String) -> Unit

data class TwoTextInputActionHolder(
    var positiveHandler: TwoTextInputAction? = null,
    var negativeHandler: DialogAction? = null,
    var neutralHandler: DialogAction? = null
)

class TwoTextInputViewModel : GenericActionShareViewModel<TwoTextInputActionHolder>() {

    private var firstInput: String = ""
    private var secondInput: String = ""

    fun setFirstInput(text: String) {
        firstInput = text
    }

    fun setSecondInput(text: String) {
        secondInput = text
    }

    fun handle(id: Long, actionType: ActionType) {
        pop(id)?.let {
            when (actionType) {
                ActionType.POSITIVE -> it.positiveHandler?.invoke(firstInput, secondInput)
                ActionType.NEGATIVE -> it.negativeHandler?.invoke()
                ActionType.NEUTRAL -> it.neutralHandler?.invoke()
            }
        }
    }

}


class TwoTextFieldDialogFragment : BaseDialogFragment() {
    private val viewModel: TwoTextInputViewModel by navGraphViewModels(R.id.root_nav_graph)
    private var dialogView: View? = null

    @SuppressLint("InflateParams")
    override fun setupView(builder: AlertDialog.Builder) {
        dialogView = activity?.layoutInflater?.inflate(R.layout.view_two_field_input, null)
        dialogView?.let {
            it.findViewById<EditText>(R.id.topTextInputField)?.let { editText ->
                editText.doAfterTextChanged { text ->
                    viewModel.setFirstInput(text?.toString() ?: "")
                }
                arguments?.alertInitialTextValue?.let { value ->
                    editText.setText(value)
                }
            }
            it.findViewById<EditText>(R.id.bottomTextInputField)?.let { editText ->
                editText.doAfterTextChanged { text ->
                    viewModel.setSecondInput(text?.toString() ?: "")
                }
                arguments?.alertInitialSecondTextValue?.let { value ->
                    editText.setText(value)
                }
            }
            it.findViewById<TextView>(R.id.topTitleLabel)?.let { textView ->
                textView.text = arguments?.alertTopLabel
            }
            it.findViewById<TextView>(R.id.bottomTitleLabel)?.let { textView ->
                textView.text = arguments?.alertBottomLabel
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
        viewModel.handle(alertId, ActionType.NEUTRAL)
    }
}


fun Fragment.presentTwoTextFieldDialog(
    title: String? = null,
    positiveTitle: String? = null,
    positiveAction: TwoTextInputAction? = null,
    negativeTitle: String? = null,
    negativeAction: DialogAction? = null,
    neutralTitle: String? = null,
    neutralAction: DialogAction? = null,
    options: Bundle? = null
) {
    val navController = (activity as? MainActivity)?.navController
    navController?.let {
        val owner = it.getViewModelStoreOwner(R.id.root_nav_graph)
        val viewModel = ViewModelProvider(owner).get(TwoTextInputViewModel::class.java)
        val holder = TwoTextInputActionHolder(positiveAction, negativeAction, neutralAction)
        val alertId = viewModel.put(holder)
        val params = Bundle().apply {
            options?.let { other ->
                this.putAll(other)
            }
            this.alertId = alertId
            this.alertTitle = title
            this.alertPositiveButton = positiveTitle
            this.alertNegativeButton = negativeTitle
            this.alertNeutralButton = neutralTitle
        }
        navController.navigate(R.id.twoTextFieldDialogFragment, params)
    }
}