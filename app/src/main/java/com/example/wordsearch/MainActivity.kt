package com.example.wordsearch

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.provider.UserDictionary
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsearch.helpers.DatabaseHelper
import com.example.wordsearch.helpers.WordRecyclerAdapter
import com.example.wordsearch.model.Words

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//Declaration
    private lateinit var allWordRecycler: RecyclerView
    private lateinit var listView: MutableList<Words>
    private lateinit var recyclerAdapter: WordRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Get teh recycler view id from content_xml
        allWordRecycler = findViewById<View>(R.id.all_words_recycler) as RecyclerView
        listView = ArrayList()
        recyclerAdapter = WordRecyclerAdapter(listView,this)

        //Linear manager or grid manager
        val mLayoutManager = LinearLayoutManager(this)

        allWordRecycler.layoutManager = mLayoutManager

        //Give the card view  fix ssize
        allWordRecycler.setHasFixedSize(true)

        //Links the recycler adapter class to the recycler view

        allWordRecycler.adapter = recyclerAdapter

        databaseHelper = DatabaseHelper(this)

        //Call a db function to execute from the inner class to execute
        GetDataFromSQLite().execute()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       val id = item.itemId
        if (id == R.id.action_add_word)
        {
            val toAddword = Intent(this, Add_word::class.java)
            toAddword.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            toAddword.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            toAddword.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(toAddword)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    //this class will help your get data from SQLite db without laggin

    // We use AyncTask whenever we want to fetch bulky work and make our app to work faster
    inner class GetDataFromSQLite: AsyncTask<Void, Void, List<Words>>()
    {
        override fun doInBackground(vararg p0: Void?): List<Words> {
            return  databaseHelper.fetchWord()
        }

        override fun onPostExecute(result: List<Words>?) {
            super.onPostExecute(result)

            //It clears the list view
            listView.clear()
            listView.addAll(result!!)
        }

    }

}

