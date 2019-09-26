package helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import model.Words

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

    //add record to Database
    fun addWords(words: Words){
        val db = this.writableDatabase

        //Inserting the values into our db
        val values = ContentValues()
        values.put(COLUMN_WORD_TITLE,words.title)
        values.put(COLUMN_WORD_MEANING,words.title)

        db.insert(TABLE_NAME,null,values)
        db.close()


    }
    fun checkWords(title: String): Boolean{

        //Specifies the array of column

        val column = arrayOf(COLUMN_WORD_ID)
        val db = this.readableDatabase

        //write selection criteria

        val selection = "$COLUMN_WORD_TITLE = ?"

        //Write selection argument

        val selectionArg = arrayOf(title)

        val cursor = db.query(TABLE_NAME,
            column,
            selection,
            selectionArg,
            null,
            null,
            null
            )
        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0){
            return true
        }
        return false

    }

    fun deleteWord(words:Words){
        val db = this.writableDatabase
        //delete record
         db.delete(TABLE_NAME,"$COLUMN_WORD_ID =?", arrayOf(words.id.toString()))


    }
    fun updateWord(words:Words){
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_WORD_TITLE,words.title)
        values.put(COLUMN_WORD_MEANING,words.meaning)

        db.update(TABLE_NAME,values,"$COLUMN_WORD_TITLE=?", arrayOf(words.id.toString()))
        db.close()
    }
    fun fetchWord():List<Words>{

        //array of columns to be fetch

        val columns = arrayOf(COLUMN_WORD_ID, COLUMN_WORD_TITLE, COLUMN_WORD_MEANING)

        //Sorting  order
        val sortOrder = "$COLUMN_WORD_MEANING ASC"
        val wordList = arrayListOf<Words>()

        val db = this.readableDatabase

        //query our Database

        val cursor = db.query(TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            sortOrder)
        if (cursor.moveToNext()){
            do{
                val word = Words(
                    id = cursor.getString(cursor.getColumnIndex(COLUMN_WORD_ID)).toInt(),
                    title = cursor.getString(cursor.getColumnIndex(COLUMN_WORD_TITLE)),
                    meaning = cursor.getString(cursor.getColumnIndex(COLUMN_WORD_MEANING)))
                wordList.add(word)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return wordList

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