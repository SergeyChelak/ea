package org.chelak.ea.database.entity

import androidx.room.*

@Entity(
    tableName = "__calculation_rate_links",
    indices = [Index(value = ["__calculation_item_uid"]), Index(value = ["__rate_uid"])],
    foreignKeys = [ForeignKey(
        entity = CalculationItem::class,
        parentColumns = ["uid"],
        childColumns = ["__calculation_item_uid"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Rate::class,
        parentColumns = ["uid"],
        childColumns = ["__rate_uid"],
        onDelete = ForeignKey.CASCADE
    )]
)
class CalculationLinkRate(
    @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "__calculation_item_uid") var calculationItemUId: Long = 0,
    @ColumnInfo(name = "__rate_uid") var rateUid: Long = 0
)