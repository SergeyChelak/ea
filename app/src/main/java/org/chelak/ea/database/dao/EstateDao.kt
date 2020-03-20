package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.Estate

@Dao
interface EstateDao: GenericDao<Estate> {

    @Query("SELECT * FROM __estates")
    fun fetchAll(): LiveData<List<Estate>>

    @Query("SELECT * FROM __estates WHERE uid = :id LIMIT 1")
    fun fetchById(id: Long): LiveData<Estate>
}