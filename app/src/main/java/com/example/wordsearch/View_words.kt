package com.example.wordsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

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


    }
}
