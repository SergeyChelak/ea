package org.chelak.ea.di

import android.content.Context
import androidx.navigation.NavController
import dagger.Module
import dagger.Provides
import org.chelak.ea.core.Repository
import org.chelak.ea.database.UserDatabase
import org.chelak.ea.ui.MainActivity
import org.chelak.ea.ui.Navigator
import java.lang.ref.WeakReference

@Module class RepositoryModule {

    private val databaseFile = "ea_develop_3.db"

    @ApplicationScope
    @Provides fun provideDatabase(context: Context): UserDatabase = UserDatabase.create(context, databaseFile)

    @ApplicationScope
    @Provides fun provideRepository(database: UserDatabase): Repository = Repository(database)

}

@Module class HostModule(activity: MainActivity) {
    private var activityReference: WeakReference<MainActivity> = WeakReference(activity)

    @ApplicationScope
    @Provides fun provideContext(): Context = activityReference.get()!!

    @ApplicationScope
    @Provides fun provideNavigator(): Navigator {
        val activity = activityReference.get()!!
        return Navigator(activity.navController)
    }

}