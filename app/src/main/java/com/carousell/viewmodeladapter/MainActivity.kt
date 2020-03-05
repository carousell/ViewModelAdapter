package com.carousell.viewmodeladapter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter =
        MainAdapter(this)
    private val list by lazy {
        List(5) { i -> "Num: $i" }
            .mapIndexed { index, string ->
                if (index % 2 == 0) {
                    ViewModelProvider(this, TextViewModel.Factory(string))
                        .get(string, TextViewModel::class.java)
                } else {
                    ViewModelProvider(this, EditViewModel.Factory(string))
                        .get(string, EditViewModel::class.java)
                }
            }
            .toMutableList()
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
            list.add(
                2, ViewModelProvider(this, TextViewModel.Factory("add new"))
                    .get("add new", TextViewModel::class.java)
            )
            adapter.setData(list)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
