## Credentials

1.Set `MARVEL_PRIVATE_KEY` and `MARVEL_PUBLIC_KEY` variables in your Environment Variables

## References, libraries and tools used in the project 

* [Android Architecture Blueprints]
Used Model View Presenter
* [Data Binding](https://developer.android.com/topic/libraries/data-binding)
Write declarative layouts and minimize the glue code necessary to bind application logic and layouts
* [AndroidX](https://developer.android.com/jetpack/androidx)
AndroidX is the open-source project that the Android team uses to develop, test, package, version and release libraries within Jetpack.
AndroidX is a major improvement to the original Android Support Library.
* [Retrofit](http://square.github.io/retrofit)
HTTP client for Android and Java
* [Gson](https://github.com/google/gson)
A Java serialization/deserialization library that can convert Java Objects into JSON and back.
* [RecyclerView](http://developer.android.com/intl/pt-br/reference/android/support/v7/widget/RecyclerView.html)
* [Glide](https://github.com/bumptech/glide)
An image loading and caching library for Android focused on smooth scrolling

## Details
The application is a assignment project.
Showcase information about Marvel's vast library of comics.
Create [Marvel Developer account](https://developer.marvel.com/) and get public and private keys. 

Hit marvel comics api url 
* http://gateway.marvel.com/v1/public/ 
fetch data and you good to go. We have limited items per request to 20 i.e. only 20 charcaters will be fetched in one go which implements pagination.

This app uses recycler view to show characters from marvel comics. On click of any character a detail page of character is shown which has character different section-
* Stories
* Comics
* Events
* Series
* links

These sections are shown using view pager fragment adapter.
