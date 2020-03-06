package com.carousell.viewmodeladapter.base

sealed class Item(
    val id: Long,
    val value: String
) {
    class Text(value: String) : Item(value = value, id = value.hashCode().toLong())
    class Edit(value: String) : Item(value = value, id = value.hashCode().toLong())
}