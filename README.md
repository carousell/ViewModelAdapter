# ViewModelAdapter

ViewModelAdapter is a project that apply MVVM pattern for RecyclerView.Adapter in Android.

## Install

With setup of Jitpack first, than add dependency in your build.gradle
```groovy
implementation 'com.github.carousell:viewmodeladapter:0.2'
```

## Concept

We want to make `Activity`/`Fragment` to be pure container and delegate the functionality to elements inside `RecyclerView` to fulfill clean and scaleable architecture.
We use ViewModel as the data source of RecyclerView, and generate corresponding ViewHolder/View that will bind with the ViewModel and observe change or execute actions.

## Usage & Example

- Create a list of `ViewModels` that extends `ItemViewModel` that represent the different items that will be displayed into the `RecyclerView`. The following methods should be implemented:
  - getKey: Provide a unique key to the ViewModel.
  - compare: Compare different `ViewModel` and return if they are identical.
    
  Example:
  ```kotlin
  class TextViewModel(private val item: TextItem) : ItemViewModel() {

      override fun getKey(): String {
          return item.key
      }
  
      override fun compare(viewModel: ItemViewModel): Boolean {
          if (viewModel is TextViewModel) {
              return viewModel.text == text
          }
          return false
      }
  }
  ```
- Create a list of `ViewHolders` that extends `ViewModelHolder` to interact with your corresponding `ViewModel`. The following methods should be implemented:
  - onBind: Subscribe to the data sources (LiveData, RxJava, etc) provided by your ViewModel.
  - onUnbind: Unsubscribe to the data sources (LiveData, RxJava, etc) provided by your ViewModel.
  
  Example:
  ```kotlin
  class TextHolder(itemView: View, private val lifecycleOwner: LifecycleOwner) :
      ViewModelHolder<TextViewModel>(itemView), Observer<String> {
  
      override fun onBind(viewModel: TextViewModel) {
          viewModel.liveData().observe(lifecycleOwner, this)
      }
  
      override fun onUnbind(viewModel: TextViewModel) {
          viewModel.liveData().removeObserver(this)
      }
  
      override fun onChanged(string: String?) {
          itemView.text.text = string
      }
  }
  ```
- Create adapter extends `ViewModelAdapter` which will handle the `ViewModel` binding for you.
  The only method need to implement:
  - onCreateViewHolder: Create the corresponding `ItemViewModel` via different modelType.
    
  Example:  
  ```kotlin
    override fun onCreateViewHolder(
        parent: ViewGroup,
        modelType: Class<out ItemViewModel>
    ): ViewModelHolder<out ItemViewModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (modelType) {
            TextViewModel::class.java -> TextHolder(
                layoutInflater.inflate(R.layout.adapter_text, parent, false),
                lifecycleOwner
            )
            EditViewModel::class.java -> EditHolder(
                layoutInflater.inflate(R.layout.adapter_edit, parent, false)
            )
            else -> throw RuntimeException("Not support")
        }
    }
  ```
Go to `./app` module for more information.

## Contributing

Thank you for being interested in contributing to this project. Check out the [CONTRIBUTING](https://github.com/carousell/ViewModelAdapter/blob/master/CONTRIBUTING.md) document for more info.

## About

<a href="https://github.com/carousell/" target="_blank"><img src="https://avatars2.githubusercontent.com/u/3833591" width="100px" alt="Carousell" align="right"/></a>

**ViewModelAdapter** is created and maintained by [Carousell](https://carousell.com/). Help us improve this project! We'd love the [feedback](https://github.com/carousell/ViewModelAdapter/issues) from you.

We're hiring! Find out more at <http://careers.carousell.com/>

## License

**ViewModelAdapter** is released under Apache License 2.0.
See [LICENSE](https://github.com/carousell/ViewModelAdapter/blob/master/LICENSE) for more details.
