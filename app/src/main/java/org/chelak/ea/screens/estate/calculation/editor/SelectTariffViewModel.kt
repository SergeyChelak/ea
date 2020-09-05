package org.chelak.ea.screens.estate.calculation.editor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.chelak.ea.core.Repository
import org.chelak.ea.screens.estate.calculation.editor.Rule
import javax.inject.Inject

class SelectTariffViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var ruleEditor: RuleEditor

    private val data = MutableLiveData<Rule>()



}