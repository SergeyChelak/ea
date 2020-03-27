package org.chelak.ea.di

import dagger.Component
import org.chelak.ea.screens.estate.details.EstateDetailsViewModel
import org.chelak.ea.screens.home.EstateListViewModel
import org.chelak.ea.screens.home.tariff.TariffsViewModel

@ApplicationScope
@Component(modules = [RepositoryModule::class, HostModule::class])
interface AppComponent {

    fun inject(obj: EstateListViewModel)
    fun inject(obj: EstateDetailsViewModel)
    fun inject(obj: TariffsViewModel)
}