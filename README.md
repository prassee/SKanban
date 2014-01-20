## SKanban
Simple Kanban system developed using Scala.js.
![SKanban](https://dl.dropboxusercontent.com/u/10783650/Screenshot%202014-01-12%2021.23.57.png "SKanban")

#### Currently Implemented Features
Though a real Kanban board can have as many as columns , I limit with only 3. Here are the list of 
features.

* works entirely offline (I have plans for an online version using Akka + websockets)
* drag and drop cards between lanes
* can save the cards on your browser (HTML5 local storage)
* restore the position of cards when reopened the same page (on the same browser)

#### Upcoming features
I am working on the following features

* archive a card 
* support for more than one board
* cutomizable lanes (other than 'Todo','Doing','Done')
