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
experience of our city *through* its food. A tracking application 
would have been an interesting tool to assist us in this journey.

### User Stories:
- As a user, I want to be able to add a restaurant as a journal entry to my journal with a rating, restaurant name, and a comment
- As a user, I want to be able to view my list of restaurants from highest to lowest rating
- As a user, I want to be able to view my the details of each journal entry
- As a user, I want to be able to add a new rating and comment on a restaurant (separate from the initial entry)
- As a user, I want to be able to save my journal to file (if I so choose), including all restaurant information
- As a user, I want to be able to load my journal from file (if I so choose), including all restaurant information

### Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by
going to the front page (if you are not already there) and clicking the button labelled "Add restaurant to journal"
- You can generate the second required action related to adding Xs to a Y by
going to the front page (if you are not already there) and clicking
the button labelled "View restaurants by top rating". This lets the user view all the
restaurants added to the journal in descending order by rating
- You can locate my visual component by running the Main class. There should be a picture displayed
on the default screen
- You can save the state of my application by going to the front page and clicking the button labelled "Save Journal file"
- You can reload the state of my application by going to the front page and clicking the button labelled 
"Load journal file"

### Phase 4: Task 2

Wed Aug 09 14:56:46 PDT 2023
Restaurant added to journal

Wed Aug 09 14:56:50 PDT 2023
Restaurant added to journal

Wed Aug 09 14:56:53 PDT 2023
Restaurant added to journal

Wed Aug 09 14:56:54 PDT 2023
Restaurants sorted by rating

Process finished with exit code 0

### Phase 4: Task 3
To improve my design, I would break down my GUI class into smaller classes. The one class contains all my GUI components,
including many fields. This can make my GUI class difficult to navigate. 
Breaking it down would hopefully improve the cohesion of my design.
Each panel in the GUI class has it own purpose and subcomponents, so they can each be separated into their own class.
The GUI class itself can be responsible for initializing and displaying the frame and panels, saving the file, and
loading the file. Additionally, to reduce repetitive code, I might create an interface that the panels would implement, 
as they currently all have a common initialization method. 

Furthermore, there is an unnecessary association between the GUI class and the Restaurant class. I would refactor 
the GUI class to have it access the Journal's restaurants through the Journal itself.
 
