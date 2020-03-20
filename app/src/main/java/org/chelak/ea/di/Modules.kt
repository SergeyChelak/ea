package org.chelak.ea.di

import android.content.Context
import dagger.Module
import dagger.Provides
import org.chelak.ea.core.Repository
import org.chelak.ea.database.UserDatabase

@Module class RepositoryModule {

    private val databaseFile = "ea_develop_1.db"

    @ApplicationScope
    @Provides fun provideDatabase(context: Context): UserDatabase = UserDatabase.create(context, databaseFile)

    @ApplicationScope
    @Provides fun provideRepository(database: UserDatabase): Repository = Repository(database)

}