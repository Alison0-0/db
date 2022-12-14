package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity2 : AppCompatActivity() {

    val dbHelper = DBHelper(this)
    val list = mutableListOf<Todo>()
    companion object{
        const val KEY = "idd"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        val ID = intent.getLongExtra(MainActivity.KEY, -1)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val editTextCity = findViewById<EditText>(R.id.editTextCity)
        val buttonADD = findViewById<Button>(R.id.buttonADD)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        val buttonEdit = findViewById<Button>(R.id.buttonEdit)
        var idnew  = 0
        if (idnew == 0) {
            val cont = dbHelper.getById(ID)

            editTextName.setText(editTextName.text.toString() + " " + cont?.name)

            editTextPhone.setText(editTextPhone.text.toString() + " " + cont?.phone.toString())
            editTextCity.setText(editTextCity.text.toString() + " " + cont?.city.toString())
        }


        buttonADD.setOnClickListener{
            val id = dbHelper.add(editTextName.text.toString(),editTextPhone.text.toString(), editTextCity.text.toString())
            list.add(Todo(id,editTextName.text.toString(),editTextPhone.text.toString(), editTextCity.text.toString()))
            idnew = id.toInt()
        }
        buttonDelete.setOnClickListener{

            dbHelper.removename( editTextName.text.toString())
        }
        buttonBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        buttonEdit.setOnClickListener{
            if (idnew == 0)
            {
                //dbHelper.update(idkey, editTextName.text.toString(), editTextPhone.text.toString(), editTextCity.text.toString())
            }
            else
            {
                dbHelper.update(idnew.toLong(), editTextName.text.toString(), editTextPhone.text.toString(), editTextCity.text.toString())
            }
        }
    }
}