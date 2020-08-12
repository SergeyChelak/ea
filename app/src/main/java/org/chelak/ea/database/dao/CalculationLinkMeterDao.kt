package org.chelak.ea.database.dao

import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.CalculationLinkMeter

@Dao
interface CalculationLinkMeterDao: GenericDao<CalculationLinkMeter> {

    @Query("SELECT * FROM __calculation_meter_links WHERE __calculation_item_uid = :calculationItemId")
    fun fetchMeterLinks(calculationItemId: Long): List<CalculationLinkMeter>

    @Query("DELETE FROM __calculation_meter_links WHERE __calculation_item_uid = :calculationItemId")
    fun removeMeterLinks(calculationItemId: Long): Int

}