package org.chelak.ea.database.entity

import androidx.room.*
import org.chelak.ea.common.BitMask

@Entity(
    tableName = "__calculation_items",
    indices = [Index(value = ["__tariff_uid"]), Index(value = ["__estate_uid"])],
    foreignKeys = [ForeignKey(
        entity = Estate::class,
        parentColumns = ["uid"],
        childColumns = ["__estate_uid"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Tariff::class,
        parentColumns = ["uid"],
        childColumns = ["__tariff_uid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CalculationItem(
    @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "__estate_uid") var estateUid: Long = 0,
    @ColumnInfo(name = "__tariff_uid") var tariffUid: Long = 0,
    @ColumnInfo(name = "__order") var order: Int = 0,
    @ColumnInfo(name = "__month_mask") var monthMask: BitMask?
)