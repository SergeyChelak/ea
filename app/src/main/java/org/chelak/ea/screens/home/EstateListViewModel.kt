package org.chelak.ea.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.ui.Navigator
import java.util.*
import javax.inject.Inject

class EstateListViewModel : ViewModel() {

    @Inject lateinit var repository: Repository
    @Inject lateinit var navigator: Navigator

    fun getEstates(): LiveData<List<Estate>> = repository.allEstates()

    fun openEstateDetails(estateId: Long) {
        navigator.openEstateDetails(estateId)
    }

    fun addEstate() {
        populateNewEntry()
    }

    private fun populateNewEntry() {
        GlobalScope.launch {
            val title = "Est ${Date().time}"
            val estate = Estate(title = title)
            repository.addEstate(estate)
        }
    }
}
