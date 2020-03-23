package org.chelak.ea.database.entity

import androidx.room.*
import java.util.*

@Entity(
    tableName = "__payments",
    indices = [Index(value = ["__estate_uid"])],
    foreignKeys = [ForeignKey(
        entity = Estate::class,
        parentColumns = ["uid"],
        childColumns = ["__estate_uid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Payment(
    @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "__estate_uid") var estateUid: Long = 0,
    @ColumnInfo(name = "__date") var date: Date?
)