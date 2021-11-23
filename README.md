# Popular Shop Android App
## Architecture 
Built using MVVM 
Separation of concern achieved by separating each layer into different classes as mentioned below:
* `ApiService` consists the api request used in the app
* `MainRepository`  invokes api calls in coroutines and handles exceptions 
* `MainViewModel` executes repository functions in viewModelScope and handles the response
* LiveData variables in view model are updated on response 
* Fragments observe LiveData variables from the view model and update UI when the value changes
LiveData boolean named `isLoading` used for updating visibility of progressBar and content. `isLoading` connected to UI layout using data binding and set to true before request in view model and to false at completion. Created binding adapters to handle visibility of UI element. 

`NetworkModule` is a Hilt module containing retrofit setup. Used dependency injection to inject  api class in repository. 

RecyclerView, connected to adapter, used for presenting sale items list. NestedScrollView used in `fragment_sale_details` to achieve scrolling for lengthier item descriptions. ViewPager2 used for creating image carousel layout in `SaleDetailsFragment`. TabLayout used for carousel position indicator. TabLayoutMediator used to connect TabLayout with ViewPager. ViewPager connected to ImagePagerAdapter to handle image loading. Adapter data set change notified in onPageSelected for ViewPager to achieve height wrapping functionality for ImageView. 
## Libraries 
* Hilt for dependency injection 
* Timber for logging 
* Retrofit for api calls 
* Glide for image loading 
## Navigation 
* Used Navigation component  for navigation between `SaleListFragment` and `SaleDetailsFragment`
* `nav_main` contains the navigation graph
* Used safe args for passing selected `saleItem` from `SaleListFragment` to `SaleDetailsFragment` to display detailed information
## Custom 
* Kotlin extensions for setting toolbar title and getting the correct picture url based on device width. 
* `getPictureUrl` extension takes in list of pictures and the position of the ViewPager,  used for the index of the item requested. Sorted picture list by width of the formatted pictures in the formats map into a new list of `FormattedPictures`. Iterated over this list and returned the formatted picture object with its width being either equal to or lesser than device width. 
* Created global variable `deviceWidth` in `PopularShopApplication` and set it in `MainActivity`
* Custom `getPrice` function in `SaleItem` to get a string type price. Used Currency to get currency symbol from `priceCurrency` and String.format to retrieve a formatted string price.    
## Api Response 
`ApiService` used for making api calls to network. 
Response mapped to `SaleListResponse` data class. Created data classes for `SaleItem` and `Meta` to retrieve information from response. Created additionally data classes for `Picture` which contained the id and the formats available. Implemented `FormattedPicture` data class to map picture url, height, and width. Used a map of String and `FormattedPicture` in `Picture` class to extract formats from response. 
Used a separate class for unsuccessful network response to allow for a different response compared to the successful calls. Mapped message to an immutable string variable, errorMessage. 