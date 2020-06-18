# ViewModelAdapter

ViewModelAdapter is a project that apply MVVM pattern for RecyclerView.Adapter in Android.

## Install

With setup of Jitpack first, than add dependency in your build.gradle
```groovy
implementation 'com.github.carousell:viewmodeladapter:0.1'
```

## Usage

1. Create list of `Item` classes represent different data type and encapsulate your data if need.
    - `key` is used when we get `ViewModel` back in ViewModelProvider.get(key, modelClass), so make sure you have unique key if there are several same item type for your data. 
2. Create list of `ViewModel` classes extends `ItemViewModel` to represent different type of item you want to display on RecyclerView.
3. Create list of `ViewHolder` classes extends `ViewModelHolder` to interact with your `ItemViewModel`.
4. Create your `Adapter` extends `ViewModelAdapter` which will handle the `ViewModel` binding for you.

See `./app` module for more information.


