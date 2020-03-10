package com.carousell.viewmodeladapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carousell.viewmodel.adapter.AutoViewModelProvider

open class TextViewModel(text: String = "") : ViewModel() {
    private val liveData = MutableLiveData<String>()

    init {
        this.liveData.value = text
    }

    fun liveData(): LiveData<String> {
        return liveData
    }

    class Factory(private val text: String = "") :
        AutoViewModelProvider<TextViewModel>() {
        override fun create(): TextViewModel {
            return TextViewModel(text)
        }
    }
}