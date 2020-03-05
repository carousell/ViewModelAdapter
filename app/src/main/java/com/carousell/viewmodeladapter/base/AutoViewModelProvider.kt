package com.carousell.viewmodeladapter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class AutoViewModelProvider<V : ViewModel> :
    ViewModelProvider.Factory {

    abstract fun create(): V

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return create() as T
    }

}