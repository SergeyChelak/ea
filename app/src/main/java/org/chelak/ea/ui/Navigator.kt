package org.chelak.ea.ui

import android.os.Bundle
import androidx.navigation.NavController
import org.chelak.ea.R

class Navigator(private val navController: NavController) {

    fun navigateBackToEstates() {
        navController.popBackStack(R.id.myEstatesFragment, false)
    }

    fun openEstateDetails(estateId: Long) {
        val bundle = Bundle().apply {
            this.estateId = estateId
        }
        navController.navigate(R.id.estateDetailsFragment, bundle)
    }

    fun openMeterList(estateId: Long) {
        val bundle = Bundle().apply {
            this.estateId = estateId
        }
        navController.navigate(R.id.action_estateDetailsFragment_to_meterListFragment, bundle)
    }

    fun openTariffDetails(tariffId: Long) {
        val bundle = Bundle().apply {
            this.tariffId = tariffId
        }
        navController.navigate(R.id.tariffDetailsFragment, bundle)
    }

}