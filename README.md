# CSC207 Software Design Project Blueprint: Code Crafters

# Team Information:
Name: Code Crafters 

Members: 

Maryam Hasanzadehkiabi 

Farhan Zahid 

Jana van Heeswyk

Ying Mu

# Project Domain:
A location-mapping & way-finding service application based around the UofT St. George campus, designed primarily for students.

# Software Specification:
This application will allow the User to input their origin location and the destination location, and the map will provide the shortest route(s). The map area is the UofT campus, with outer boundaries at Bloor St. West, Bay St., Spadina Ave., and College St. The User can find destinations via a search engine that returns locations on the map related to their query. The User can find and choose a specific destination they have in mind, or they can choose from a list of categories of places (e.g. cafes, libraries, etc.) and receive suggestions of possible destinations in the chosen category. They can also receive general destination suggestions that don’t require search input, which are the most popular destinations on campus. The User can see their location search history and are able to re-use past searches, or delete them, either all at once or one-by-one. The User can also vote “YES” or “NO” to the routes suggested to them to indicate if they did or didn’t like them. The approval rate of a route (as provided by other users) is shown to the User when the route is given.

# User Stories:
In the context of our application, a user story would involve:

<-> A motive/reason for the User to interact with a feature of our application

<-> A end goal/result of the User’s interaction with the particular feature; and how it highlights the feature’s strengths

As defined in our software specification, we plan to implement two priority features (routes, search), as well as multiple secondary features (history, feedback). Thus, a user story will vary based on the motive behind using an app feature (what the User is “looking for”). 

***Each subcategory indicates a substantial feature of our app, while each bullet point breaks down the feature into a user story pertaining to a unique aspect of the feature.

## 1. ROUTE NAVIGATION(Team user stories):
A -> B: John wants to go from one class to another as fast as possible. He inputs both his current location and the destination on the app, and the map shows him the shortest possible route he can take to get to this destination. 

A -> X -> B: John needs to stop by the bookstore before going to his class. So he inputs the origin location, the bookstore location and his class location on the map, the map gives the shortest path possible.

ROUTE-RANKING: John wants to compare between multiple suggested routes, so the app returns a list of possible routes between locations A and B, ranked objectively based on time and proximity.

##  2. SEARCH ENGINE:
PINPOINT: John wants to go to Gerstein Library, so he inputs “gerstein library” into the search engine; the map only shows him the on-campus location as a destination.

TEXT-BASED QUERY: John wants to find any library, so he inputs keyword/phrase “library”/“libra”; the search tab returns a list/sublist of library destinations, shows each location on the map, and allows him to select one as a destination. 

MOST-POPULAR/DEFAULT: John wants to find the most population locations on-campus, so he can search without inputting a prompt, and the app will return the most searched/visited locations. 

APP-SUGGESTED: John goes to the search engine and chooses “cafe” from a list of suggestions provided by the app. The app then returns a list of cafes on-campus.

## 3. VIEW/EDIT SEARCH HISTORY:

REUSE: John wants to go to a location he has already searched for, so he accesses his app search history, finds the location, and puts it as the destination. 

REMOVE: John does not want others to see his search history on the app, so he opens the search history and deletes some prompts from his search history.

CLEAR: John wants to remove his complete search history on the app, so he clears it all at once.

 ## 4. SIMPLE USER FEEDBACK:
 
VOTE: For a recent route he took, John wants to vote on whether or not the app-suggested route was the best possible route. He can vote “YES” OR “NO” to the suggested route, and this rating would be factored in beside any route ranking list as “X% of users recommended this route” 

## Proposed Entity List: 

|Entity Name|Instance variables|
| --- | --- |
| Map |Intersections, Streets (must be walkable), Locations |
| Location| Address, Name, Keyword(s), Category(s) |
|Route |Start and endpoint, Distance, Approval rating, Estimate time consumed|
|User |Origin location, Destination location, Search history, Route rating, Identification|
|Search History | Locations, Search prompts|



## Scheduled Meeting Times + Mode of Communication:

Meeting time outside of lab: Weekly on Sundays at 3:00pm

Mode of Communication: WhatsApp group chat, Zoom online meeting

