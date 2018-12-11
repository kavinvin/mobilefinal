package th.ac.kmitl.a59070009.mobilefinal.fragments

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    val db = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val DB_NAME = "healthy.db"
        val DB_VERSION = 2
        val CREATE = "CREATE TABLE IF NOT EXISTS users (id TEXT PRIMARY KEY, name TEXT NOT NULL, age INTEGER NOT NULL, password NOT NULL)"
    }

    fun create(user: User): Long {
        val userEntity = ContentValues()
        userEntity.put("id", user.id)
        userEntity.put("name", user.name)
        userEntity.put("age", user.age)
        userEntity.put("password", user.password)
        return db.insert("users", null, userEntity);
    }

    fun login(id: String, password: String): User? {
        val columns = arrayOf<String>("id", "name", "age", "password")
        val cursor = db.query(true, "users", columns, "id = '$id' AND password = '$password'", null, null, null, null, null)
//        val cursor = db.query(true, "users", columns, null, null, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
        };
        if (cursor.count == 0) {
            return null
        }
        return User(
            cursor.getString(0),
            cursor.getString(1),
            cursor.getInt(2),
            cursor.getString(3)
        )
    }



}

data class User(val id: String, val name: String, val age: Int, val password: String)

