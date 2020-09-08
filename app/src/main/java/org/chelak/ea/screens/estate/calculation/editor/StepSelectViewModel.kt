package org.chelak.ea.screens.estate.calculation.editor

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class StepSelectViewModel : ViewModel() {

    @Inject
    lateinit var ruleEditor: RuleEditor
    var stepId: Long = 0L

    fun update(items: List<SelectionListItem>) {
        ruleEditor.commit(stepId, items)
    }

}