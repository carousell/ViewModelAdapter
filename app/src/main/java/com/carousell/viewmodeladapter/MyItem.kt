package com.carousell.viewmodeladapter

import com.carousell.viewmodel.adapter.Item

sealed class MyItem(
    id: Long,
    value: String
) : Item(id, value) {
    class Text(value: String) : MyItem(value = value, id = value.hashCode().toLong())
    class Edit(value: String) : MyItem(value = value, id = value.hashCode().toLong())
}