package org.chelak.ea.screens.estate.calculation.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.chelak.ea.core.Repository
import org.chelak.ea.screens.estate.calculation.manager.Rule
import javax.inject.Inject

class CalculationEditViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    private val data = MutableLiveData<Rule>()



}