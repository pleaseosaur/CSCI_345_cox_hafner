# Deadwood: "The Cheapass Game of Acting Badly"

---
### Authors:

---
* Andrew Cox
* Peter Hafner

### Course: 

---

* CSCI 345: Object Oriented Design
* Western Washington University
* _Spring 2023_

---

## Introduction:

---

Deadwood is a fast-paced board game about actors, acting, and the thrill-filled life of a 
wandering bit player. It's perfect for 2 to 6 players, and still decent for 7 or 8. Play 
time is about 60 minutes. 

---

## Compiling and Running:

---

To compile the game from the terminal, navigate to CSCI_345_cox_hafner/Deadwood and run 
the following command:

```javac *.java```

To run the game from the terminal, run the following command:

```java Main```

To compile and run the game from an IDE, use your IDE's built-in run command or run button. 

The external files used for initial game setup have been converted to input streams, which 
should allow them to be correctly read in from the terminal or an IDE.

If issues arise while attempting to run the game via a build tool or IDE, please first attempt 
compiling and running via the terminal. If issues persist, please contact the authors. 

---

## Gameplay:

---

When the game has started, the user will be prompted to enter the number of players. 
The user will then be given an option to rename the players. If the user chooses to rename 
the players, they will be prompted to enter a new name for each player. If the user chooses 
not to rename the players, the player names will remain the default names. 

Once the players have been named, the game will begin. 

The current player's location and relevant stats will be analyzed and a unique set of 
allowed actions will be displayed. The player will be prompted to select from the actions 
presented. 

Available actions are displayed with a corresponding number. To select an action, the player 
should enter the number corresponding to the action they wish to take. 

The game is designed to restrict the players actions to only those that are allowed by the 
rules of the game. If the player attempts to perform an action that is not allowed, the 
game will notify the player and prompt them to select a different action. 

When ```1. end turn``` is the only available action remaining, select it to end the current 
player's turn. The next player will then be prompted to begin their turn.

The game will continue until all rounds (days) have been completed. At the end of the game, 
the scores will be calculated and the winner(s) displayed. 

---

## Gameplay Restrictions:

---

* If a player is at the Trailer, their only option is to move.
* If a player is at the Casting Office, they may upgrade and/or move.
* If a player is at a Set location and have not yet taken a role, they may take a role if they so choose.
* If a player is at a Set location and has taken a role:
    * If the role is a starring role (on the card), they may rehearse or act.
    * If the role is an extra role (off the card), they may only act.

---

## Universal Commands:

---

The following commands are available to use at any time during any player's turn:

```help``` - Displays this list of available commands and their descriptions.

```quit``` - Ends the game.

```back``` - Returns the player to the previous menu.

```stats``` - Displays the player's current stats, including location and role. 

```board``` or ```view``` - Displays all locations and their neighbors

These commands will be displayed at the beginning of the game. 

---

## Known Issues:

---

* The ```quit``` command occasionally acts as an interrupt to the current prompt and needs to 
be entered again to end the game. 
