package org.chelak.ea.ui

import androidx.navigation.NavController
import org.chelak.ea.R
import org.chelak.ea.common.Logger
import javax.inject.Inject

class Navigator(val navController: NavController) {

    fun openEstateDetails(estateId: Long) {
        navController.navigate(R.id.action_myEstatesFragment_to_estateDetailsFragment)
    }

    fun openMeterList(estateId: Long) {
        navController.navigate(R.id.action_estateDetailsFragment_to_meterListFragment)
    }

}