# ViewModelAdapter

ViewModelAdapter is a project that apply MVVM pattern for RecyclerView.Adapter in Android.

## Install

With setup of Jitpack first, than add dependency in your build.gradle
```groovy
implementation 'com.github.carousell:viewmodeladapter:0.1'
```

## Concept

We want to make `Activity`/`Fragment` to be pure container and delegate the functionality to elements inside `RecyclerView` to fulfill clean and scaleable architecture.
We use ViewModel as the data source of RecyclerView, and generate corresponding ViewHolder/View that will bind with the ViewModel and observe change or execute actions.

## Usage

1. Create list of `ViewModel` classes extends `ItemViewModel` to represent different type of item you want to display on `RecyclerView`.
2. Create list of `ViewHolder` classes extends `ViewModelHolder` to interact with your corresponding `ViewModel`.
3. Create adapter extends `ViewModelAdapter` which will handle the `ViewModel` binding for you.

See `./app` module for more information.

## Contributing

Thank you for being interested in contributing to this project. Check out the [CONTRIBUTING](https://github.com/carousell/ViewModelAdapter/blob/master/CONTRIBUTING.md) document for more info.

## About

<a href="https://github.com/carousell/" target="_blank"><img src="https://avatars2.githubusercontent.com/u/3833591" width="100px" alt="Carousell" align="right"/></a>

**ViewModelAdapter** is created and maintained by [Carousell](https://carousell.com/). Help us improve this project! We'd love the [feedback](https://github.com/carousell/ViewModelAdapter/issues) from you.

We're hiring! Find out more at <http://careers.carousell.com/>

## License

**ViewModelAdapter** is released under Apache License 2.0.
See [LICENSE](https://github.com/carousell/ViewModelAdapter/blob/master/LICENSE) for more details.
