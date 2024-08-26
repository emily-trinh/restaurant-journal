# Restaurant Journal

## Restaurant Experience Recording Application

### Goals and Interest
The goal of this application is to provide a space 
where users can easily record their restaurant experiences.
This journal can then be referenced to, for example, when
deciding which restaurant to go to for a given occasion. The
application is meant to be used by **anybody** looking to keep track
of restaurants, cafes, etc. Possible features of this application 
include entering personal ratings, cuisine, occasion the establishment
is suited for, location, and if the establishment is 
accessible by transit. Additional features may include a ranking
system based on selected features, or perhaps a chart/graph to
categorize restaurants.

A project surrounding restaurant experience is inspired by a goal
my friends and I had set for ourselves when we lived in the same
city. We decided we would try to eat at all the restaurants 
in our small city at least once. Ultimately, our goal was to
experience of our city through its food. A tracking application 
would have been a great tool to assist us in our journey.


### Instructions for use

- You can add entries by going to the front page (if you are not already there) and clicking the button labelled "Add restaurant to journal"
- To let the user view all the restaurants added to the journal by rating in descending order, you can
going to the front page (if you are not already there) and click
the button labelled "View restaurants by top rating"
- You can save the state of this application by going to the front page and clicking the button labelled "Save Journal file"
- You can reload the state of thus application by going to the front page and clicking the button labelled 
"Load journal file"


### Future Improvements
To improve my design, I would break down my GUI class into smaller classes. The one class contains all my GUI components,
including many fields. This can make my GUI class difficult to navigate. 
Breaking it down would hopefully improve the cohesion of my design.
Each panel in the GUI class has it own purpose and subcomponents, so they can each be separated into their own class.
The GUI class itself can be responsible for initializing and displaying the frame and panels, saving the file, and
loading the file. Additionally, to reduce repetitive code, I might create an interface that the panels would implement, 
as they currently all have a common initialization method. 

Furthermore,  I would refactor the GUI class to have it access the Journal's restaurants through the Journal itself.
 
