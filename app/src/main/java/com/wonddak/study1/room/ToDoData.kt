package com.wonddak.study1.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoData(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var todo: String,
    var check: Boolean = false

)