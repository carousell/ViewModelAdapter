package com.carousell.viewmodeladapter.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carousell.viewmodel.adapter.AutoViewModelProvider
import com.carousell.viewmodel.adapter.ItemViewModel
import com.carousell.viewmodeladapter.MyItem

open class TextViewModel(item: MyItem.Text) : ItemViewModel(item) {
    private val liveData = MutableLiveData<String>()
    private val text = item.key

    init {
        this.liveData.value = text
    }

    fun liveData(): LiveData<String> {
        return liveData
    }

    class Factory(private val item: MyItem.Text) :
        AutoViewModelProvider<TextViewModel>() {
        override fun create(): TextViewModel {
            return TextViewModel(item)
        }
    }
}