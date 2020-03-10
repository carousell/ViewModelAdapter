package com.carousell.viewmodeladapter

import com.carousell.viewmodel.adapter.Item

sealed class MyItem(
    key: String
) : Item(key) {
    class Text(key: String) : MyItem(key)
    class Edit(key: String) : MyItem(key)
}