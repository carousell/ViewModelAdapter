package com.carousell.viewmodel.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * AutoViewModelProvider is a factory which helps to create ViewModels for you.
 */
abstract class AutoViewModelProvider<V : ViewModel> :
    ViewModelProvider.Factory {

    /**
     * Abstract method which creates a new ViewModel instance
     */
    abstract fun create(): V

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return create() as T
    }

}