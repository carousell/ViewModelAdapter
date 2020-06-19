package com.carousell.viewmodel.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * AutoViewModelProvider is a factory class help to create ViewModel for you.
 */
abstract class AutoViewModelProvider<V : ViewModel> :
    ViewModelProvider.Factory {

    /**
     * Abstract method where should be the place for ViewModel to created
     */
    abstract fun create(): V

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return create() as T
    }

}