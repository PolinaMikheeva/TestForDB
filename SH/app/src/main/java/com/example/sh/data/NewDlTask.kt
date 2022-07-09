package com.example.sh.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//каждый класс entity представляет собой sqlite таблицу, таблицу можно назвать иначе, чем класс
@Entity(tableName = "dlTasks_table")

//каждая организация нуждается в главном ключе
//зачем.. пока не поняла
//в ColumnInfo указываем имя столбца, если нам надо, чтоб оно отличалось от имени переменной
data class NewDlTask(@PrimaryKey @ColumnInfo(name = "dlTask") val dlTask: String)
