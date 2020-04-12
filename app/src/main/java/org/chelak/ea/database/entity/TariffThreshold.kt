package org.chelak.ea.database.entity

import androidx.room.*
import java.math.BigDecimal

@Entity(
    tableName = "__tariff_thresholds",
    indices = [Index(value = ["__tariff_uid"])],
    foreignKeys = [ForeignKey(
        entity = Tariff::class,
        parentColumns = ["uid"],
        childColumns = ["__tariff_uid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class TariffThreshold(
    @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "__tariff_uid") var tariffUid: Long = 0,
    @ColumnInfo(name = "__value") var value: Long? = 0,
    @ColumnInfo(name = "__price") var price: BigDecimal?
)