package org.chelak.ea.ui

import androidx.navigation.NavController
import org.chelak.ea.R
import org.chelak.ea.common.Logger

class Navigator(private val navController: NavController) {

    fun openEstateDetails(estateId: Long) {
        val container = ArgumentContainer.builder()
            .putEstateId(estateId)
            .build()
        navController.navigate(R.id.action_myEstatesFragment_to_estateDetailsFragment, container.getBundle())
    }

    fun openMeterList(estateId: Long) {
        val container = ArgumentContainer.builder()
            .putEstateId(estateId)
            .build()
        navController.navigate(R.id.action_estateDetailsFragment_to_meterListFragment, container.getBundle())
    }

    fun openTariffDetails(tariffId: Long) {
        val args = ArgumentContainer.builder().putTariffId(tariffId).build()
        navController.navigate(R.id.tariffDetailsFragment, args.getBundle())
    }

}