package com.example.wordsearch.helpers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsearch.R
import com.example.wordsearch.View_words
import com.example.wordsearch.model.Words

class WordRecyclerAdapter(private val listWord: List<Words>, internal var context:Context): RecyclerView.Adapter<WordRecyclerAdapter.WordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        //Binding the card view on the recycler
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.words_list_card,parent,false)
        return WordViewHolder(itemView)

    }

    override fun getItemCount(): Int {
    //Size
        return listWord.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        //the data collected from the card view will be bind to the card view
        holder.textTitle.text = listWord[position].title

        //Set an onclick listener to title so that when click it will take us to anothe activsizeity
        holder.itemView.setOnClickListener(View.OnClickListener {
            val i = Intent(context, View_words::class.java)

            //Passing all the values collecected to the next activity
            i.putExtra("id", listWord[position].id)
            i.putExtra("title", listWord[position].title)
            i.putExtra("meaning",listWord[position].meaning)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)


        })

    }


    inner class WordViewHolder(view:View): RecyclerView.ViewHolder(view){

        val textTitle: TextView
        init {
            textTitle = view.findViewById(R.id.et_addword) as TextView
        }
    }

}