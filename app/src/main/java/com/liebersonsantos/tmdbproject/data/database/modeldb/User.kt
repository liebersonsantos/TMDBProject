package com.liebersonsantos.tmdbproject.data.database.modeldb

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user")
@Parcelize
data class User(
    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "phone")
    val phone: String
) : Parcelable