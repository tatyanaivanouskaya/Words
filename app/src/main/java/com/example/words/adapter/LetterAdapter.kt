package com.example.words.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.words.DetailActivity
import com.example.words.R

class LetterAdapter: RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    private val list = ('A').rangeTo('Z').toList()

    class LetterViewHolder(view: View): RecyclerView.ViewHolder(view){
        val buttonItem: Button = view.findViewById(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return LetterViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        holder.buttonItem.text = list[position].toString()
        holder.buttonItem.setOnClickListener {
            val context = holder.buttonItem.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.LETTER, holder.buttonItem.text.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}