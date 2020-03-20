package org.chelak.ea.database

import android.content.Context
import androidx.room.*
import org.chelak.ea.common.BitMask
import org.chelak.ea.database.dao.EstateDao
import org.chelak.ea.database.entity.Estate

@TypeConverters(UserDatabase.Addons::class)
@Database(entities = [Estate::class],
    exportSchema = false,
    version = 1)
abstract class UserDatabase : RoomDatabase() {

    companion object Addons {
        fun create(context: Context, file: String): UserDatabase = Room.databaseBuilder(
            context, UserDatabase::class.java, file
        ).build()

        @TypeConverter
        fun toBitMask(value: Int): BitMask = BitMask(value)

        @TypeConverter
        fun toInt(bitMask: BitMask?): Int = bitMask?.raw ?: 0
    }

    abstract fun estateDao(): EstateDao
}