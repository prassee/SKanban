## SKanban
Simple Kanban system developed using Scala.js.
![SKanban](https://dl.dropboxusercontent.com/u/10783650/Screenshot%202014-01-12%2021.23.57.png "SKanban")

##### Currently Implemented Features
Though a real Kanban board can have as many as columns , I limit with only 3. 
Here are the list of features.

* works entirely offline (I have plans for an online version using Akka + web-sockets).
* drag and drop cards between lanes.
* can save the cards on your browser (HTML5 local storage).
* restore the position of cards when reopened the same page (on the same browser).
* clear board deletes all the cards which have been created.

##### Upcoming features
I am working on the following features

* archive a card.
* support for more than one board.
* customizable lanes (other than 'To-do', 'Doing', 'Done').
* colored cards to represent priority.
* tagged cards