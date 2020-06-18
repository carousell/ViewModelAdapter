package com.carousell.viewmodeladapter

import androidx.lifecycle.*
import com.carousell.viewmodel.adapter.Item
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
    private fun getData(): MutableList<Item> {
        return IntRange(0, 10000).map { index ->
            if (index % 2 == 0) {
                MyItem.Text(index.toString(), "Item $index")
            } else {
                MyItem.Edit(index.toString(), "Item $index")
            }
        }.toMutableList()
    }

    private fun generateViewModels(list: List<Item>) {
        liveData.value = list.map {
            generateViewModel(it)
        }
    }

    private fun generateViewModel(item: Item): ItemViewModel {
        return ViewModelProvider(
            itemViewModelStore,
            getFactory(item)
        ).get(item.key, getViewModelClass(item))
    }

    private fun getViewModelClass(item: Item): Class<out ItemViewModel> = when (item) {
        is MyItem.Text -> TextViewModel::class.java
        is MyItem.Edit -> EditViewModel::class.java
        else -> throw IllegalArgumentException("getViewModelClass failed")
    }

    private fun getFactory(item: Item): ViewModelProvider.Factory = when (item) {
        is MyItem.Text -> TextViewModel.Factory(item)
        is MyItem.Edit -> EditViewModel.Factory(item)
        else -> throw IllegalArgumentException("getFactory failed")
    }

    fun addNewItem() {
        val item = MyItem.Edit(
            System.currentTimeMillis().toString(),
            "add new" + System.currentTimeMillis()
        )
        val list = liveData.value?.toMutableList()
        list?.add(2, generateViewModel(item))
        liveData.value = list
    }
}