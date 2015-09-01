# FoursquareDemo
A demonstration of using Foursquare to explore and present venues at a given location.

This demo app has the following features:
- Allows a user to end a 'location' and searches Foursquare for the popular venues at that location.
- Displays the Names of the venues along with the main picture for that venue if available.

The app demonstrates the following:
- Material Design toolbar that scrolls away when results are scrolled up.
- CardViews that scroll in a RecyclerView (v7 Compat library for device coverage)
- Image loading using Picasso
- JSON parsing using Jackson 
- REST API consumption using Retrofit
- JVM Unit Testing of Java components (JSON parsing and Retrofit API calls) (Build Variants -> Unit Tests)
- Mockito Mocking
- On device testing of Android Components (AsyncTask, Activity) (Build Variants -> Android Instrumentation Tests)
