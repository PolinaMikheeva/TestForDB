package com.example.sh.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

//аннотирую класс в качестве бд рума и в параметрах указываю организацию и версию бд
//каждая организация отвечает за таблицу данных, созданных в бд
//еще тут есть параметр exportSchema, но он имеет значение false для избежания build warning, а на будущее надо понять, как ей пользоваться, чтобы проверять текущую схему в vcs
@Database(entities = [NewDlTask::class], version = 1)

//класс бд должен быть абстрактным и иметь тип RoomDatabase
abstract class DlTasksRoomDatabase : RoomDatabase() {

    abstract fun dlTaskDao(): NewDlTaskDao

    companion object {
        //Используем Singleton pattern, чтобы предотвратить множественные одновременные открытия бд
        @Volatile
        private var INSTANCE: DlTasksRoomDatabase? = null

        //получаем доступ к каждому dao через абстрактный getter метод, проходясь по каждому @Dao
        fun getDatabase(context: Context, scope: CoroutineScope): DlTasksRoomDatabase {

            //возвращаем значение INSTANCE, если оно не null, иначе создаем бд
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DlTasksRoomDatabase::class.java,
                    "dlTasks_database"
                ).build()

                INSTANCE = instance
                // return instance
                instance
                //getDatabase возвращает singleton, он создает RoomDatabase объект из класса DlTasksRoomDatabase и называет его dlTasks_database
            }
        }
    }
}