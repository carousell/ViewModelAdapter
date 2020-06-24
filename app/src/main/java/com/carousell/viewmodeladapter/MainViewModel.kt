package com.carousell.viewmodeladapter

import androidx.lifecycle.*
import com.carousell.viewmodel.adapter.ItemViewModel
import com.carousell.viewmodeladapter.items.EditViewModel
import com.carousell.viewmodeladapter.items.TextViewModel

class MainViewModel : ViewModel() {

    private val liveData = MutableLiveData<List<ItemViewModel>>()
    private val itemViewModelStore = ViewModelStore()

    init {
        generateViewModels(getData())
    }

    fun getLiveData(): LiveData<List<ItemViewModel>> {
        return liveData
    }

    fun refresh() {
        itemViewModelStore.clear()
        generateViewModels(getData())
    }

    // Generate data
    private fun getData(): MutableList<MyItem> {
        return IntRange(0, 10000).map { index ->
            if (index % 2 == 0) {
                TextItem(index.toString(), "Item $index")
            } else {
                EditItem(index.toString(), "Item $index")
            }
        }.toMutableList()
    }

    private fun generateViewModels(list: List<MyItem>) {
        liveData.value = list.map {
            generateViewModel(it)
        }
    }

    private fun generateViewModel(item: MyItem): ItemViewModel {
        return ViewModelProvider(
            itemViewModelStore,
            getFactory(item)
        ).get(item.key, getViewModelClass(item))
    }

    private fun getViewModelClass(item: MyItem): Class<out ItemViewModel> = when (item) {
        is TextItem -> TextViewModel::class.java
        is EditItem -> EditViewModel::class.java
    }

    private fun getFactory(item: MyItem): ViewModelProvider.Factory = when (item) {
        is TextItem -> TextViewModel.Factory(item)
        is EditItem -> EditViewModel.Factory(item)
    }

    fun addNewItem() {
        val item = EditItem(
            System.currentTimeMillis().toString(),
            "add new" + System.currentTimeMillis()
        )
        val list = liveData.value?.toMutableList()
        list?.add(2, generateViewModel(item))
        liveData.value = list
    }
}