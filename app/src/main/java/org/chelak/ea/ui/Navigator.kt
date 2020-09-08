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

    fun openTariffDetails(tariffId: Long) {
        val bundle = Bundle().apply {
            this.tariffId = tariffId
        }
        navController.navigate(R.id.tariffDetailsFragment, bundle)
    }

    fun openMeterDetails(meterId: Long) {
        val bundle = Bundle().apply {
            this.meterId = meterId
        }
        navController.navigate(R.id.meterDetailsFragment, bundle)
    }

    fun openRateList(estateId: Long) {
        val bundle = Bundle().apply {
            this.estateId = estateId
        }
        navController.navigate(R.id.rateListFragment, bundle)
    }

    fun openCalculationSettings(estateId: Long) {
        val bundle = Bundle().apply {
            this.estateId = estateId
        }
        navController.navigate(R.id.calculationListFragment, bundle)
    }

    fun openSelectScreen(title: String, stepId: Long, isMultipleChoice: Boolean) {
        val bundle = Bundle().apply {
            this.navigationTitle = title
            this.stepIdentifier = stepId
            this.isMultipleChoice = isMultipleChoice
        }
        navController.navigate(R.id.stepSelectFragment, bundle)
    }
}