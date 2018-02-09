# Android propotype: 
## Display a list of Gnomes with their professional descriptions an a detailed view when needed (with their friend list) 

* MVP pattern applied. 
* Model calls based on event triggering and response handling on the presenter side. UI decouple from model calls.
* Filtering: The list of gnomes have two criteras of filtering. Filter with the Gnome name or filter on the professions separated by comma.
* DTOs response mapping with Gson and Retrofit2.
* Images caching and loading with Glide.
* Error handling on API call fail with event triggering (load dummy Gnome list in this case).
* Event bus injected with Dagger2.

Try it the apk in /app/release or clone this repo and build it yourself.

Library ussages: Retrofit, Gson, Glide, Dagger 2, Otto (Event bus)
