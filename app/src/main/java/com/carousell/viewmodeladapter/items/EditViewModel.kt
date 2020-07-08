package com.carousell.viewmodeladapter.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carousell.viewmodel.adapter.ItemViewModel
import com.carousell.viewmodeladapter.EditItem

class EditViewModel(private val item: EditItem) : ItemViewModel() {
    private var text = item.data

    override fun getKey(): String {
        return item.key
    }

    override fun compare(viewModel: ItemViewModel): Boolean {
        if (viewModel is EditViewModel) {
            return viewModel.text == text
        }
        return false
    }

    fun getValue(): String {
        return text
    }

    fun setValue(text: String) {
        this.text = text
    }

    class Factory(private val item: EditItem) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EditViewModel(item) as T
        }
    }
}