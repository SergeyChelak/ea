package org.chelak.ea.database.entity

import androidx.room.*
import java.util.*

@Entity(
    tableName = "__meter_values",
    indices = [Index(value = ["__meter_uid"])],
    foreignKeys = [ForeignKey(
        entity = Meter::class,
        parentColumns = ["uid"],
        childColumns = ["__meter_uid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class MeterValue (
    @PrimaryKey(autoGenerate = true) var uid: Long?,
    @ColumnInfo(name = "__date") var date: Date?,
    @ColumnInfo(name = "__value") var value: Long?,
    @ColumnInfo(name = "__meter_uid") var meterUid: Long?,
    @ColumnInfo(name = "__is_paid") var isPaid: Boolean = false
)