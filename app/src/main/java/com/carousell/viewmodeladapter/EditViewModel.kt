package com.carousell.viewmodeladapter

import androidx.lifecycle.ViewModel
import com.carousell.viewmodel.adapter.AutoViewModelProvider

class EditViewModel(private var text: String = "") : ViewModel() {

    fun getValue(): String {
        return text
    }

    fun setValue(text: String) {
        this.text = text
    }

    class Factory(private val text: String = "") :
        AutoViewModelProvider<EditViewModel>() {
        override fun create(): EditViewModel {
            return EditViewModel(text)
        }
    }
}