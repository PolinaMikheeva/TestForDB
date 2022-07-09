//ViewModel связывает ui и репозиторий
//ViewModel помогает фрагментам обмениваться информацией
//ViewModel является частью lifecycle библиотеки

//activities и фрагменты отображают информацию на экране, пока ViewModel хранит и обрабатывает
//информацию для ui

//LiveData - это видимый хранитель информации
//В отличие от Flow, LiveData чувствительна к lifecycle, то есть к компонентам Activity и Fragment

//ViewModel трансформирует данные из репозитория, Flow в LiveData и передает список заданий как
//LiveData в UI, что обеспечивает в нем автоматическое обновление информации

//все корутины работают в CoroutineScope. C прекращением его работы, перестают работать все корутины.
//viewModelScope - расширение функций viewModel, позволяющая работать сферами(scope)
package com.example.sh.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class DlTasksViewModel(private val repository: DlTasksRepository) : ViewModel() {
//заводим класс, получающий как парметр репозиторий и выдающий ViewModel
//для получения ViewModel в конструкторе класса нужен только репозиторий

    val allDlTasks: LiveData<List<NewDlTask>> = repository.allDlTasks.asLiveData()
    //добавили переменную LiveData, чтобы кэшировать список заданий
    //инициализировали LiveData с помощью allDlTasks Flow из репозитория
    //затем благодаря вызову asLiveData конвертировали Flow в LiveData

    fun insert(dlTask: NewDlTask) = viewModelScope.launch {
    //создали функцию вставки, которая вызывает из репозитория метод вставки(это чтобы инкапсулировать вставку из ui)
    //мы запускаем новый корутин и вызывает suspend функцию вставки из репозитория
        repository.insert(dlTask)
    }
}

class DlTasksViewModelFactory(private val repository: DlTasksRepository) : ViewModelProvider.Factory {
//я вообще не понимаю, зачем этот класс, но написано, что ViewModelProvider.Factory получает как параметр зависимости
//нужные для создания ViewModel - репозиторий
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DlTasksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DlTasksViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
