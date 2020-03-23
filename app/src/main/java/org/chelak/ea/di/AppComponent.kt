package org.chelak.ea.di

import dagger.Component
import org.chelak.ea.screens.estate.EstateDetailsViewModel
import org.chelak.ea.screens.home.EstateListViewModel

@ApplicationScope
@Component(modules = [RepositoryModule::class, HostModule::class])
interface AppComponent {

    fun inject(obj: EstateListViewModel)
    fun inject(obj: EstateDetailsViewModel)

}