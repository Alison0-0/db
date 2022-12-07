package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    val dbHelper = DBHelper(this)
    val list = mutableListOf<Todo>()


    private lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list.addAll(dbHelper.getAll())

        adapter = RecyclerAdapter(list) {

            dbHelper.remove(list[it].id.toInt())
            // адаптеру передали обработчик удаления элемента
            list.removeAt(it)
            adapter.notifyItemRemoved(it)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter



        val Textvvod = findViewById<EditText>(R.id.Textvvod)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        buttonPlus.setOnClickListener {
           // val id = dbHelper.add(Textvvod.text.toString(),"89133755105", "city")
           // list.add(Todo(id,Textvvod.text.toString(),"89133755105","city"))
           // adapter.notifyItemInserted(list.lastIndex)
        }

    }




}
