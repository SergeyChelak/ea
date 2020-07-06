package org.chelak.ea.di

import dagger.Component
import org.chelak.ea.screens.estate.calculation.CalculationListViewModel
import org.chelak.ea.screens.estate.meters.MeterDetailsViewModel
import org.chelak.ea.screens.estate.details.EstateDetailsViewModel
import org.chelak.ea.screens.estate.rates.RateListViewModel
import org.chelak.ea.screens.home.EstateListViewModel
import org.chelak.ea.screens.home.tariff.TariffDetailsViewModel
import org.chelak.ea.screens.home.tariff.TariffsViewModel

@ApplicationScope
@Component(modules = [RepositoryModule::class, HostModule::class, Utilities::class])
interface AppComponent {

    fun inject(obj: EstateListViewModel)
    fun inject(obj: EstateDetailsViewModel)
    fun inject(obj: MeterDetailsViewModel)
    fun inject(obj: TariffsViewModel)
    fun inject(obj: TariffDetailsViewModel)
    fun inject(obj: RateListViewModel)
    fun inject(obj: CalculationListViewModel)
}