package com.example.androidtemplatedsl.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Empty(@PrimaryKey val uid: Int, val userName: String)
