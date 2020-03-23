package org.chelak.ea.database.entity

import androidx.room.*

@Entity(
    tableName = "__calculation_meter_links",
    indices = [Index(value = ["__calculation_item_uid"]), Index(value = ["__meter_uid"])],
    foreignKeys = [ForeignKey(
        entity = CalculationItem::class,
        parentColumns = ["uid"],
        childColumns = ["__calculation_item_uid"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Meter::class,
        parentColumns = ["uid"],
        childColumns = ["__meter_uid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CalculationLinkMeter(
    @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "__calculation_item_uid") var calculationItemUId: Long = 0,
    @ColumnInfo(name = "__meter_uid") var meterUid: Long = 0
)