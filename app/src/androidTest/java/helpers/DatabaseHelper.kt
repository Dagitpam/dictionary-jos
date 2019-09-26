package helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {
    //Create table for words
    private val CREATE_WORD_TABLE = "CREATE TABLE "+ TABLE_NAME+ "("+ COLUMN_WORD_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMN_WORD_TITLE+" TEXT,"+ COLUMN_WORD_MEANING+" TEXT,"+")"
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //Create a companion object
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "wordDB.db"
        private val TABLE_NAME = "words"
        private val COLUMN_WORD_ID ="word_id"
        private val COLUMN_WORD_TITLE ="word_title"
        private  val COLUMN_WORD_MEANING = "word_meaning"
    }

}