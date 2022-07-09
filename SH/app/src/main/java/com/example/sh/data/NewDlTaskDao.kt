//наблюдение за изменениями в бд
package com.example.sh.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
//Dao должен быть либо интерфейсом, либо абстрактным классом
//аннотация нужна, чтобы идентифицировать класс как dao для room

interface NewDlTaskDao {

    @Query("SELECT * FROM dlTasks_table ORDER BY dlTask ASC")
    //ASC = ascending order(возрастающий порядок)
    fun getDlTasks(): Flow<List<NewDlTask>>
    //функция возвращает список заданий
    //используем Flow(асинхронная последовательность переменных), чтобы наблюдать за изменениями данных


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //Insert аннотация - метод dao, благодаря которому нам не надо подключаться к SQL(так же, как и с Delete и Update)
    //данная стратегия onConflict позволяет игнорировать повторяющиеся слова
    suspend fun insert(dlTask: NewDlTask)
    //декларирует suspend функцию(ф-цию, выполняющуюся внутри корутины) c добавлением одного слова

    @Query("DELETE FROM dlTasks_table")
    //т. к. нет аннотаций с удалением нескольких организаций, используем генерируемый query
    //@Query требует, чтобы мы отправляли sql запросы как строковые параметры(чтобы были доступны сложные запросы чтения и прочие операции)
    suspend fun deleteAll()
    //декларирует suspend функцию удаления всех слов
}
