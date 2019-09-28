package com.example.wordsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.wordsearch.helpers.DatabaseHelper
import com.example.wordsearch.model.Words

class Update_word : AppCompatActivity() {
    private lateinit var et_title:EditText
    private lateinit var et_meaning:EditText
    //Declare a global variable
    private lateinit var update_title:String
    private lateinit var update_meaning:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_word)

        //Getting the ids from view words activity
        val id_get = intent.getIntExtra("id",-1)
        val title_get = intent.getStringExtra("title")
        val meaning_get = intent.getStringExtra("meaning")
        //getting values empty from view word xml
        et_title = findViewById(R.id.et_update_word)
        et_meaning = findViewById(R.id.et_update_meaning_word)

         et_title.setText(title_get)
        et_meaning.setText(meaning_get)

        val btn_update = findViewById<Button>(R.id.bt_update_word_btn)
       btn_update.setOnClickListener(View.OnClickListener {
           //Get the update values
           update_title = et_title.text.toString().trim()
           update_meaning = et_meaning.text.toString().trim()

           //create a database helper class
           val db_helper = DatabaseHelper(this)

           //Insert updated values to the Word class object

           val words = Words(id = id_get, title =  update_title, meaning = update_meaning)

           //Cal/ the update word function from thbHelper class
           db_helper.updateWord(words)
           //Redirect to Main activity
           val toMain = Intent(this,View_words::class.java)
           startActivity(toMain)
       })

    }
}
