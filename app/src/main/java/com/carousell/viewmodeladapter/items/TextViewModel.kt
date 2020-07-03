package com.carousell.viewmodeladapter.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carousell.viewmodel.adapter.ItemViewModel
import com.carousell.viewmodeladapter.TextItem

open class TextViewModel(private val item: TextItem) : ItemViewModel() {
    private val liveData = MutableLiveData<String>()
    private val text = item.data

    init {
        this.liveData.value = text
    }

    override fun getKey(): String {
        return item.key
    }

    override fun compare(viewModel: ItemViewModel): Boolean {
        if (viewModel is TextViewModel) {
            return viewModel.text == text
        }
        return false
    }

    fun liveData(): LiveData<String> {
        return liveData
    }

    class Factory(private val item: TextItem) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TextViewModel(item) as T
        }
    }
}