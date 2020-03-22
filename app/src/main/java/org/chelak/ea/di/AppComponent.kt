package org.chelak.ea.di

import dagger.Component
import org.chelak.ea.screens.estate.EstateDetailsViewModel
import org.chelak.ea.screens.home.MyEstatesViewModel
import org.chelak.ea.ui.Navigator

@ApplicationScope
@Component(modules = [RepositoryModule::class, HostModule::class])
interface AppComponent {

    fun inject(obj: MyEstatesViewModel)
    fun inject(obj: EstateDetailsViewModel)

}