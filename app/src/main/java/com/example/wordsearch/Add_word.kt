package com.example.wordsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.wordsearch.helpers.DatabaseHelper
import com.example.wordsearch.model.Words

class Add_word : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)
        val addBtn:Button = findViewById(R.id.bt_add_word_btn)
        addBtn.setOnClickListener(View.OnClickListener {
            //calling the user function here
            wordInput()

        })

    }
    //function for collecting values id
    fun wordInput(){
        val title = findViewById<EditText>(R.id.et_addword).text.toString()
        val meaning = findViewById<EditText>(R.id.et_addmeaning).text.toString()
        checkValues(title,meaning)

    }
    //to check if field area is empty
    fun checkValues(title:String, meaning:String){
        var msg = ""

        if (title.trim() == ""){

            msg = "Title field empty"
        }
        else if (meaning.trim() == ""){
            msg = "Meaning field empty "
        }
        else{
            // Inserting values to db
            val databaseHelper = DatabaseHelper(this)

            val words = Words(title = title, meaning = meaning)
            databaseHelper.addWords(words)
            msg = "Word added successfully"

            val toMain = Intent(this,MainActivity::class.java)
            startActivity(toMain)
            finish()


        }
        displayTost(msg)


    }
    fun displayTost (msg: String){
        Toast.makeText(this, msg,Toast.LENGTH_LONG).show()
    }


}
