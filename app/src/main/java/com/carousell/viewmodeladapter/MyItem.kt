package com.carousell.viewmodeladapter

import com.carousell.viewmodel.adapter.Item

class MyItem {
    class Text(key: String, val data: String) : Item(key)
    class Edit(key: String, val data: String) : Item(key)
}