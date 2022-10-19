package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var i = 0
    val key = "ключ"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            i = savedInstanceState.getInt(key)
        }

        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        render()
        buttonPlus.setOnClickListener {
            i++
            render()

        }
        buttonMinus.setOnClickListener {
            i--
            if (i <= 0) {
                Toast.makeText(applicationContext, "хватит!", Toast.LENGTH_SHORT).show()
                i = 0
            }
            render()

        }


    }

    fun render() {
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = i.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(key, i)
        super.onSaveInstanceState(outState)
    }
}