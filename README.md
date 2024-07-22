
# Project Domain:
A location-mapping & way-finding service application based around the UofT St. George campus, designed primarily for students.

My specific Use-case for Phase 1:

TEXT-BASED QUERY: John wants to find any library, so he inputs keyword/phrase “library”/“libra”; the search tab returns a list/sublist of library destinations, shows each location on the map, and allows him to select one as a destination. 

The steps i followed and made java classes:
1) data access layer (PlacesDAO)
2) data access layer implementation (Places Implementation)
3) TextSearch_InputBoundary
4) TextSearch_OutputBoundary
5) TextSearch_InputData
6) TextSearch_OutputData
7) TextSearch_Interactor
8) TextSearch_Controller
9) i also made TextSearch file = which is the main Use case
10) TextSearch_UseCase_test


if you run TextSearch, it will ask you to input a keyword that is a text search, and it will show results. 
