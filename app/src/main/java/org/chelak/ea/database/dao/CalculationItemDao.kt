package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.CalculationItem

@Dao
interface CalculationItemDao: GenericDao<CalculationItem> {

    @Query("SELECT * FROM __calculation_items WHERE __estate_uid = :estateId ORDER BY __order")
    fun fetchCalculationItems(estateId: Long): LiveData<List<CalculationItem>>

    @Query("SELECT * FROM __calculation_items WHERE uid = :id LIMIT 1")
    fun fetchById(id: Long): CalculationItem

    @Query("SELECT MAX(__order) FROM __calculation_items WHERE __estate_uid = :estateId")
    fun lastOrder(estateId: Long): Int

}