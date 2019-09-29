package com.example.wordsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class Update_word : AppCompatActivity() {
    private lateinit var et_title: EditText
    private  lateinit var et_meaning: EditText

    private lateinit var update_title

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_word)
    }
}
