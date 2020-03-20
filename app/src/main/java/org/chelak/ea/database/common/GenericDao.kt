package org.chelak.ea.database.common

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface GenericDao<T> {

    @Insert
    fun insert(obj: T): Long

    @Delete
    fun delete(obj: T): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: T): Int

}