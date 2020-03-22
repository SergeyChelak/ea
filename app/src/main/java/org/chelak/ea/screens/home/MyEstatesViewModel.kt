package org.chelak.ea.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.ui.Navigator
import javax.inject.Inject

class MyEstatesViewModel : ViewModel() {

    @Inject lateinit var repository: Repository
    @Inject lateinit var navigator: Navigator

    fun getEstates(): LiveData<List<Estate>> = repository.allEstates()

    fun openEstateDetails(estateId: Long) {
        navigator.openEstateDetails(estateId)
    }
}
