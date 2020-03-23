package org.chelak.ea.database.dao

import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.TariffThreshold

@Dao
interface TariffThresholdDao: GenericDao<TariffThreshold> {

    @Query("SELECT * FROM __tariff_thresholds WHERE __tariff_uid = :tariffUid")
    fun fetchThresholds(tariffUid: Long): List<TariffThreshold>

    @Query("SELECT * FROM __tariff_thresholds WHERE uid = :id LIMIT 1")
    fun fetchById(id: Long): TariffThreshold
}