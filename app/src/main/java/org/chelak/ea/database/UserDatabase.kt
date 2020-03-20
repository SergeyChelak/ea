package org.chelak.ea.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.chelak.ea.database.dao.EstateDao
import org.chelak.ea.database.entity.Estate

@Database(entities = [Estate::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, file: String): UserDatabase = Room.databaseBuilder(
            context, UserDatabase::class.java, file
        ).build()
    }

    abstract fun estateDao(): EstateDao
}