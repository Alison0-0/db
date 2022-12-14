package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Todo(
    val id: Long,
    val name: String,
    val phone : String,
    val city: String
) {

}

class DBHelper (context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object {
        // версия БД
        const val DATABASE_VERSION = 1
        // название БД
        const val DATABASE_NAME = "mydb"
        // название таблицы
        const val TABLE_NAME = "todos"
        // названия полей
        const val KEY_ID = "id"
        const val KEY_TITLE = "name"
        const val KEY_PHONE = "phone"
        const val KEY_CITY ="city"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE $TABLE_NAME (
                $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_TITLE TEXT NOT NULL,
                $KEY_PHONE TEXT NOT NULL,
                $KEY_CITY TEXT
            )""")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // обновление БД
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAll(): List<Todo>
    {val result = mutableListOf<Todo>()
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME, null, null, null,
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val idIndex: Int = cursor.getColumnIndex(KEY_ID)
            val nameIndex: Int = cursor.getColumnIndex(KEY_TITLE)
            val phoneIndex: Int = cursor.getColumnIndex(KEY_PHONE)
            val cityIndex: Int = cursor.getColumnIndex(KEY_CITY)
            do {
                val todo = Todo(
                    cursor.getLong(idIndex),
                    cursor.getString(nameIndex),
                    cursor.getString(phoneIndex),
                    cursor.getString(cityIndex)
                )
                result.add(todo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return result
    }



    fun add(name: String,phone: String,city: String): Long {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, name)
        contentValues.put(KEY_PHONE,phone)
        contentValues.put(KEY_CITY,city)
        val id = database.insert(TABLE_NAME, null, contentValues)
        close()
        return id
    }

    fun update(id: Long, name: String,phone: String,city: String) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, name)
        contentValues.put(KEY_PHONE,phone)
        contentValues.put(KEY_CITY,city)
        database.update(TABLE_NAME, contentValues, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }

    fun remove(id: Long) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }
    fun removename(name: String) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$KEY_TITLE = ?", arrayOf(name))
        close()
    }
    fun getById(id: Long): Todo? {
        var result: Todo? = null
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME, null, "$KEY_ID = ?", arrayOf(id.toString()),
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val idIndex: Int = cursor.getColumnIndex(KEY_ID)
            val nameIndex: Int = cursor.getColumnIndex(KEY_TITLE)
            val phoneIndex: Int = cursor.getColumnIndex(KEY_PHONE)
            val cityIndex: Int = cursor.getColumnIndex(KEY_CITY)
            result = Todo(
                cursor.getLong(idIndex),
                cursor.getString(nameIndex),
                cursor.getString(phoneIndex),
                cursor.getString(cityIndex)
            )
        }
        cursor.close()
        return result
    }

    fun getByName(name: String): Todo? {
        var result: Todo? = null
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME, null, "$KEY_TITLE = ?", arrayOf(name),
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val idIndex: Int = cursor.getColumnIndex(KEY_ID)
            val nameIndex: Int = cursor.getColumnIndex(KEY_TITLE)
            val phoneIndex: Int = cursor.getColumnIndex(KEY_PHONE)
            val cityIndex: Int = cursor.getColumnIndex(KEY_CITY)
            result = Todo(
                cursor.getLong(idIndex),
                cursor.getString(nameIndex),
                cursor.getString(phoneIndex),
                cursor.getString(cityIndex)
            )
        }
        cursor.close()
        return result
    }

    fun removeAll() {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, null, null)
        close()
    }



}