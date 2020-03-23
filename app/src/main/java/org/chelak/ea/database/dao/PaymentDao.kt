package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.Payment

@Dao
interface PaymentDao: GenericDao<Payment> {

    @Query("SELECT * FROM __payments")
    fun fetchAll(): LiveData<List<Payment>>

    @Query("SELECT * FROM __payments WHERE __estate_uid = :estateId")
    fun fetchForEstateId(estateId: Long): LiveData<List<Payment>>

}