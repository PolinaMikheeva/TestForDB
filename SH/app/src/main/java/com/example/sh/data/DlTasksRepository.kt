package com.example.sh.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class DlTasksRepository(private val newDlTaskDao: NewDlTaskDao) {
//конструктор репозитория принимает dao, вместо всей базы данных, так как dao уже содержит все
//методы чтения и записи

    val allDlTasks: Flow<List<NewDlTask>> = newDlTaskDao.getDlTasks()
    //инициализируем лист заданий через flow из room
    //это можно сделать благодаря функции getDlTasks, которая возвращает flow
    //room делит запросы на отдельные треды

    @Suppress("RedundantSuspendModifier")
    //suspend modifier подсказывает компилятору, что следующая функция должна быть вызвана
    //из предыдущей корутины или suspend функции

    @WorkerThread
    //room принимает запросы из main треда
    suspend fun insert(dlTask: NewDlTask) {
        newDlTaskDao.insert(dlTask)
    }
}

