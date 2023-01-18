package com.example.android.roomwordssample

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Citation(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0, @ColumnInfo(name = "anime") val anime: String, @ColumnInfo(name = "character") val character: String, @ColumnInfo(name = "quote") val quote: String)
