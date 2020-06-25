package org.chelak.ea.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.ui.Navigator
import javax.inject.Inject

class EstateListViewModel : ViewModel() {

    @Inject lateinit var repository: Repository
    @Inject lateinit var navigator: Navigator

    fun getEstates(): LiveData<List<Estate>> = repository.allEstates()

    fun openEstateDetails(estateId: Long) {
        navigator.openEstateDetails(estateId)
    }

    fun addEstate(name: String) {
        GlobalScope.launch {
            val estate = Estate(title = name)
            val id = repository.insertEstate(estate)
            withContext(Dispatchers.Main) {
                openEstateDetails(id)
            }
        }
    }

}
