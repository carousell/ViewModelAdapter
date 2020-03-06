package com.carousell.viewmodeladapter.base

sealed class Item(val value: String) {
    class Text(value: String) : Item(value)
    class Edit(value: String) : Item(value)
}