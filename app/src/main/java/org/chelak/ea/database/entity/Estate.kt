package org.chelak.ea.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "__estates")
data class Estate(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "__title") var title: String?
)