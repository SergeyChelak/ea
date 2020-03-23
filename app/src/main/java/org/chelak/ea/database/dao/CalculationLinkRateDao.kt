package org.chelak.ea.database.dao

import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.CalculationLinkRate

@Dao
interface CalculationLinkRateDao: GenericDao<CalculationLinkRate> {

    @Query("SELECT * FROM __calculation_rate_links WHERE __calculation_item_uid = :calculationItemId")
    fun fetchRateLinks(calculationItemId: Long): List<CalculationLinkRate>
}