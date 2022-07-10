package com.example.sh

import android.app.Application
import com.example.sh.data.DlTasksRepository
import com.example.sh.data.DlTasksRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TasksApplication: Application() {

    // Нет необходимости отменять эту область видимости,
    // так как она будет удалена вместе с процессом
    val applicationScope = CoroutineScope(SupervisorJob())


    // Использование ленивых, поэтому база данных и
    // репозиторий создаются только тогда, когда они нужны
    // а не при запуске приложения
    val database by lazy { DlTasksRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { DlTasksRepository(database.dlTaskDao()) }
}