package org.chelak.ea.database.entity

import androidx.room.*
import java.math.BigDecimal

@Entity(
    tableName = "__payment_items",
    indices = [Index(value = ["__payment_uid"])],
    foreignKeys = [ForeignKey(
        entity = Payment::class,
        parentColumns = ["uid"],
        childColumns = ["__payment_uid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PaymentItem(
    @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "__payment_uid") var paymentUid: Long = 0,
    @ColumnInfo(name = "__title") var title: String?,
    @ColumnInfo(name = "__price") var price: BigDecimal?,
    @ColumnInfo(name = "__value") var value: Long?,
    @ColumnInfo(name = "__previous") var previous: Long?,
    @ColumnInfo(name = "__current") var current: Long
)