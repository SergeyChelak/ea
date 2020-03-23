package org.chelak.ea.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.chelak.ea.common.EstateType

@Entity(tableName = "__estates")
data class Estate(
    @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "__title") var title: String?,
    @ColumnInfo(name = "__type") var type: EstateType = EstateType.GENERAL
)