package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.Rate

@Dao
interface RateDao: GenericDao<Rate> {

    companion object {
        const val fetchRequest = "SELECT * FROM __rates WHERE __estate_uid = :estateId"
    }

    @Query("SELECT * FROM __rates WHERE __estate_uid = :estateId")
    fun fetchRates(estateId: Long): LiveData<List<Rate>>

    //@Query("SELECT * FROM __rates WHERE __estate_uid = :estateId")
    @Query(fetchRequest)
    fun getRates(estateId: Long): List<Rate>

    @Query("SELECT * FROM __rates WHERE uid = :id LIMIT 1")
    fun fetchById(id: Long): Rate

}