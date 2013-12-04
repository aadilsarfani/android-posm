Developers:
Aadil Sarfani - as45473
Clay Smalley  - ccs2365

This app, called OSM Editor, is a an app that allows users to input points of interest into the OSM database. OSM, Open Street Maps, is an open source alternative to Google Maps. Points of interest may be hospitals, restaurants, schools, etc.

How to use:
On the first screen the user can move around, zoomin and out the OSM map and place the point of interest under the pin. Then the user can choose category (currently dummy names), for example Education, Retail, etc etc. And then the user can choose a subcategory, for example, High School. Then the user can (not currently implemented) enter text input such as the name, phone number, etc.

Features that have been implemented -
1. Embedding the OSM map
2. Tested, working code for communicating with OSM servers - not yet glued
3. Other menus to choose category/subcategory

Additional features not yet implemented include:
1. Duplicate checking (within a prespecified, small radius) so that you don't get two entries for the same place
2. GPS following - if the GPS is on, then upon opening the app, or when the user touches the "GPS" button, the map centers on the user's current location (this was not part of the prototype but we plan to add it)

All you have to do is run the apk. Note: currently the category and subcategory names are dummy names, GPS isn't yet implemented, and the user can't enter the name, phone number, etc of the point of interest.

The only library we used was OSMDroid https://code.google.com/p/osmdroid/ , which gives us an OSM equivalent of the MapView class

All the code you see in 'src' was written by us - classes are APIConnection, ChooserMapView, Finalizer, LocationChooser, CategoryChooser, SubcategoryChooser

*** Important Note: ***
Even though in terms of functionality, the app is a smaller percentage of the final product at this time, about 70% of the work has been done - in terms of writing the code, understanding the OSM api, and understanding the parts of the Android api that we will need.