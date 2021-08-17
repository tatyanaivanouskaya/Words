package com.example.words.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.words.DetailActivity
import com.example.words.R

class WordAdapter(private val letter: String, val context: Context):
    RecyclerView.Adapter<WordAdapter.WordVieHolder>() {

    lateinit var filteredWords: List<String>
    init {
        val words = context.resources.getStringArray(R.array.words).toList()
        filteredWords = words
            .filter { a: String -> a.startsWith(letter, ignoreCase = true) }
            .shuffled()
            .take(5)
            .sorted()
    }

    class WordVieHolder(view: View): RecyclerView.ViewHolder(view){
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordVieHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return WordVieHolder(layout)
    }

    override fun onBindViewHolder(holder: WordVieHolder, position: Int) {
        holder.button.text = filteredWords[position]

        holder.button.setOnClickListener {
            val queryUrl: Uri = Uri.parse("${DetailActivity.SEARCH}${filteredWords[position]}")
            val intent = Intent(Intent.ACTION_VIEW,queryUrl)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return filteredWords.size
    }
}