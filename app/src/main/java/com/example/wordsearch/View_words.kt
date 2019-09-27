package com.example.wordsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.wordsearch.helpers.DatabaseHelper
import com.example.wordsearch.model.Words

class View_words : AppCompatActivity() {
    private  lateinit var title : TextView
    private lateinit var meaning: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_word)
        //Collecting values from RecyclerAdapter class
        val id_word = intent.getIntExtra("id",-1)
        val title_word = intent.getStringExtra("title")
        val meaning_word = intent.getStringExtra("meaning")
        //get the ids of view word activity
        title = findViewById(R.id.tv_view_word_title)
        meaning = findViewById(R.id.tv_view_word_meaning)

        //Displaying the values on view word activity

        title.setText(title_word)

        meaning.setText(meaning_word)

        //Delete and update user

        val delete_btn = findViewById<Button>(R.id.bt_view_word_delete)
        delete_btn.setOnClickListener(View.OnClickListener {
            //create a db instance
            val db_helper = DatabaseHelper(this)

            val word = Words(id = id_word, title = "", meaning = "")
            db_helper.deleteWord(word)
            //Redirect the user to main activity

            val toMain = Intent(this,MainActivity::class.java)
            toMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            toMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            toMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(toMain)
            finish()

            Toast.makeText(this, "Word deleted successfully", Toast.LENGTH_LONG).show()
        })
    }
}
