package org.chelak.ea.screens.estate.calculation.editor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.chelak.ea.R
import org.chelak.ea.core.StringResource
import org.chelak.ea.screens.estate.calculation.editor.RuleEditor
import org.chelak.ea.ui.dialog.AlertModel
import javax.inject.Inject

class RuleReviewViewModel: ViewModel() {

    @Inject
    lateinit var ruleEditor: RuleEditor
    @Inject
    lateinit var stringResource: StringResource

    var alertData = MutableLiveData<AlertModel>()
        private set

    fun save() {
        ruleEditor.save()
    }

    fun delete() {
        alertData.value = AlertModel(
            title = stringResource.getString(R.string.dialog_title_warning),
            message = stringResource.getString(R.string.dialog_confirm_undone_action),
            positiveTitle = stringResource.getString(R.string.btn_yes),
            positiveAction = {
                ruleEditor.delete()
                alertData.value = null
            },
            negativeTitle = stringResource.getString(R.string.btn_no),
            negativeAction = { alertData.value = null }
        )
    }

}