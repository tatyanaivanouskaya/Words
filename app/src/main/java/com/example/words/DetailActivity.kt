package com.example.words

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.words.adapter.LetterAdapter
import com.example.words.adapter.WordAdapter

class DetailActivity : AppCompatActivity() {
    var isLinearLayoutManager = true
    lateinit var recyclerView: RecyclerView
    lateinit var letterId: String

    companion object{
        val LETTER = "letter"
        val SEARCH = "https://www.google.com/search?q="
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        letterId = intent.extras?.getString(LETTER).toString()
        recyclerView = findViewById(R.id.recycler_view)
        chooseLayout()
    }
    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = WordAdapter(letterId, this)
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val buttonLayout = menu?.findItem(R.id.action_switch_layout)
        setIcon(buttonLayout)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}