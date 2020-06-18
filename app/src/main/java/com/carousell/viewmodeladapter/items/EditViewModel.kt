package com.carousell.viewmodeladapter.items

import com.carousell.viewmodel.adapter.AutoViewModelProvider
import com.carousell.viewmodel.adapter.ItemViewModel
import com.carousell.viewmodeladapter.MyItem

class EditViewModel(item: MyItem.Edit) : ItemViewModel(item) {
    private var text = item.data

    fun getValue(): String {
        return text
    }

    fun setValue(text: String) {
        this.text = text
    }

    class Factory(private val item: MyItem.Edit) :
        AutoViewModelProvider<EditViewModel>() {
        override fun create(): EditViewModel {
            return EditViewModel(item)
        }
    }
}