package org.chelak.ea.database.entity

import androidx.room.*
import java.math.BigDecimal

@Entity(
    tableName = "__rates",
    indices = [Index(value = ["__estate_uid"])],
    foreignKeys = [ForeignKey(
        entity = Estate::class,
        parentColumns = ["uid"],
        childColumns = ["__estate_uid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Rate(
    @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "__estate_uid") var estateUid: Long = 0,
    @ColumnInfo(name = "__title") var title: String? = null,
    @ColumnInfo(name = "__value") var value: BigDecimal? = null
)