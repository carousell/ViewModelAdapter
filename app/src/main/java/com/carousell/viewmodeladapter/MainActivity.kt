package com.carousell.viewmodeladapter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.carousell.viewmodeladapter.base.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = MainAdapter(this, this)

    private val list by lazy {
        IntRange(0, 4).map { index ->
            if (index % 2 == 0) {
                Item.Text("Item $index")
            } else {
                Item.Edit("Item $index")
            }
        }.toMutableList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter.setData(list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add) {
            list.add(2, Item.Text("add new"))
            adapter.setData(list)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
