package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY = "idd"
    }
    val dbHelper = DBHelper(this)
    val list = mutableListOf<Todo>()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list.addAll(dbHelper.getAll())

        adapter = RecyclerAdapter(list) {



            //dbHelper.getById(list[it].id.toLong())
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(KEY,list[it].id)
            startActivity(intent)


        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter



        val Textvvod = findViewById<EditText>(R.id.Textvvod)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        buttonPlus.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }

    }




}
