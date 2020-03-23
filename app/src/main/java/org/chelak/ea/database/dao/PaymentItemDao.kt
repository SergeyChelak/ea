package org.chelak.ea.database.dao

import androidx.room.Dao
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.PaymentItem

@Dao
interface PaymentItemDao: GenericDao<PaymentItem> {
}