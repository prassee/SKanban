## SKanban
Simple __Kanban__ system developed using __Scala.js__.
![SKanban](https://dl.dropboxusercontent.com/u/10783650/skanbanlatest.png "SKanban")

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
* markdown editor support for card 'Description'
* double click on any card would show panel to edit card details
* customizable lanes (other than 'To-do', 'Doing', 'Done').
* colored cards to represent priority. (__done__)
* tagged cards