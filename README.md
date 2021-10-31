#  Fantasy Football Java Application - A CPSC 210 Project 
Author: Kevin Nguyen </br>
Text below was taken from the README I had to write for the project

</br>
I am a big fan of the NFL, not so because I am a football fan, but more so because I am a massive fantasy 
football fan. This program should be able for you to act as a commissioner for the league, you'd be able to
name your league, get your friends to join. Your friends should then be able to choose their team names, 
draft players, record their players stats every game day and view the league standing as well as view the
players individual stats. 

This program is meant for anyone who's a fan of fantasy football and want to compete in an offline, casual
league. This project is of great interest to me as I love football, and as I support the New York Giants, 
the closest thing I get to celebration on Sunday is when my fantasy team destroy my friends' while the Giants
and our defense go and let me down again. I've always been interested about the inner workings of fantasy
football apps because they seem so complex and well-made, so I wanted to give this project a try.

**User Stories List:**

- As a user, I would like create a new league and name it
- As a user, I would like to create a new team and name it
- As a user, I would like to draft a player into my team
- As a user, I would like to be able to enter my player weekend stats from ESPN and compute the score 
  according to the algorithm that ESPN uses for their fantasy football program. Algorithm is as followed:
    - 6 points for throwing, receiving or rushing for a touchdowns 
    - 1 points for every 10 yards rushing or receiving
    - 1 points for every 25 yards throwing
    - -2 points for any fumble by any player
    - -2 points for any interceptions thrown by the quarterback

**Phase 2:**

- As a user, I would like to save all my leagues with all my data in it
- As a user, I would like to load all my leagues with all my data in it
- As a user, I would like to be reminded and given the option to save before quitting the program

**Phase 4: Task 2**

- I chose to implement the map interface in my project in the model.League class. I used it to store a list of draft-able
  players in which their positions were the key. 

 **Phase 4: Task 3**

- If I had more time to work on the project. I think I would have made the gui package a lot neater and reduce a lot of 
  coupling and code repetition. I think I would have reduced most of the windows where users enter names for their teams/
  leagues into 1 class with multiple constructors that would take different arguments. Now having learnt of different 
  design patterns, I think I would have maybe explored the composite pattern for some of my classes.  There are also a lot
  of semantic coupling in the GUI package as I had to make a lot of frames and panels; if I had more time I think I could
  have refactored their initialization into big methods that would be ran multiple times throughout the code, I will then
  proceed to put all the classes in the gui package into 1 class to make everything neater. 
