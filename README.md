# FourSquareAi

Author: Balázs Kóti
License: GNU GPLv3
Copyleft: 2016, Balázs Kóti
Created for educational purposes

## 0. License
	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.

## 1. Installation and Build
- If not installed, first you need to install maven
- from commandline rund maven with javafx build goal: 
	- `mvn clean compile jfx:build-jar`
- in `target` folder, start the built jar file!
	- `java -jar FourSquare-1.0-SNAPSHOT.jar`
	
## 2. Requirements
- Java 1.8 (or newer)
- Screen resolution: `700x650` pixels
- 5MB free disk space
- Windows/Linux/Mac OS (compiling for Android is possible)

## 3. Game properties
- Maximum number of players: *virtually infinite* (including Human and Ai players)
- Maximum size of the game board: *virtually infinite* (coordinates stored as integers)
- Original version of the game: http://2-szemelyes.jatek-online.hu/four_square.php
- Ai reference: https://dea.lib.unideb.hu/dea/handle/2437/130106

## 4. Gameplay
- Start the game executable
- On the top toolbar use one of the `Add player` buttons
	- [♥] Human player will be controlled by a human player
	- [♦] Ai player will be controlled by the computer
- On the top toolbar you can see the active player highlighted
	- The icon indicates if player is a [♥] Human or [♦] Ai player
	- If you hover over a player icon, you will see the score
- If a [♥] Human player is active, the player shoud place a square on the game board
- The color of the grid will indicate the color of the player
- If an [♦] AI player is active, you should wait for the computer until the indicator is active
- As the board size is infinite you can move the actual *viewport* on it using the arrow buttons on the toolbar (←, ↓, ↑, →)
- If a player reaches 100 points of score the player will win the game
- You can restart a game using the *New Game* button
