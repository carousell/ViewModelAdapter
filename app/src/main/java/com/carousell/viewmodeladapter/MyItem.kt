package com.carousell.viewmodeladapter

sealed class MyItem(
    open val key: String
)

data class TextItem(
    override val key: String,
    val data: String
) : MyItem(key)

data class EditItem(
    override val key: String,
    val data: String
) : MyItem(key)