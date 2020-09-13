package org.chelak.ea.ui

import android.os.Bundle
import androidx.navigation.NavController
import org.chelak.ea.R

class Navigator(private val navController: NavController) {

    fun popToEstates() {
        navController.popBackStack(R.id.myEstatesFragment, false)
    }

    fun pushEstateDetails(estateId: Long) {
        val bundle = Bundle().apply {
            this.estateId = estateId
        }
        navController.navigate(R.id.estateDetailsFragment, bundle)
    }

    fun pushTariffDetails(tariffId: Long) {
        val bundle = Bundle().apply {
            this.tariffId = tariffId
        }
        navController.navigate(R.id.tariffDetailsFragment, bundle)
    }

    fun pushMeterDetails(meterId: Long) {
        val bundle = Bundle().apply {
            this.meterId = meterId
        }
        navController.navigate(R.id.meterDetailsFragment, bundle)
    }

    fun pushRateList(estateId: Long) {
        val bundle = Bundle().apply {
            this.estateId = estateId
        }
        navController.navigate(R.id.rateListFragment, bundle)
    }

    fun pushCalculationSettings(estateId: Long) {
        val bundle = Bundle().apply {
            this.estateId = estateId
        }
        navController.navigate(R.id.calculationListFragment, bundle)
    }

    fun pushSelectScreen(title: String, stepId: Long, isMultipleChoice: Boolean) {
        val bundle = Bundle().apply {
            this.navigationTitle = title
            this.stepIdentifier = stepId
            this.isMultipleChoice = isMultipleChoice
        }

        navController.navigate(R.id.stepSelectFragment, bundle)
    }

    fun pushRuleReview() {
        navController.navigate(R.id.ruleReviewFragment)
    }

    fun popToCalculationSettings() {
        navController.popBackStack(R.id.calculationListFragment, false)
    }
}