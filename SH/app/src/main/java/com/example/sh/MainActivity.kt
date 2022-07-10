package com.example.sh

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sh.data.DlTasksViewModel
import com.example.sh.data.WordViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController
    private lateinit var addNewTask: EditText
    private val wordViewModel: DlTasksViewModel by viewModels {
        WordViewModelFactory((application as TasksApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController

        addNewTask = findViewById(R.id.et_add)

        val addButton = findViewById<ImageButton>(R.id.btn_add)
        addButton.setOnClickListener{
            val replyIntent = Intent()
            if (TextUtils.isEmpty(addNewTask.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = addNewTask.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    fun addTask(view: View) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.add_task, null)
        val editText  = dialogLayout.findViewById<EditText>(R.id.et_add)
        builder.setView(dialogLayout)
        builder.setPositiveButton("OK") { dialogInterface, i -> dialogInterface }
        builder.setNegativeButton("Отмена") { dialogInterface, i -> dialogInterface }
        builder.show()
    }

    fun operations(view: View) {

        val items = arrayOf("Редактировать", "Удалить", "Отмена")
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setItems(items) { dialog, which -> items[0]}
            show()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}