# Rover Mars
Java implementation of a Rover on Mars moving, following instructions, on a plateau represented by a Board.

# Requirements
- Java 25
- Maven

# How to run
### Compile into jar executable
`mvn package`
### Run executable
`java -jar target/RoverMars-1.0-SNAPSHOT.jar input.txt`

# Hypothesis

Input file parsing is strict. The whole file must be syntactically correct. If there is any typo, the whole program will stop.

There are rules to follow in order to be syntactically correct:
- Coordinate is two series of two non-negative integers separated with a space (eg: `100 50`)
- Only one Coordinate have to be present on the first line, which represents the Board size
- Coordinates present on the first line have to be defined between 0 and MAX_INT
- In the lines where a new rover is defined, it has to be exactly one Coordinate, a space, then a single character cardinal orientation: `N`, `E`, `S` or `W` (case-sensitive)
- In lines where there are rover instructions, all characters present here have to only be `L`, `R` or `M` without spaces

Invalid input is an error and stops the program, while a move off the Board will not stop it.
If the parsing is syntactically correct, here is a choice I made concerning rover movement:
`If a rover receives an instruction to move outside the Board, it will ignore the instruction, then continue to execute remaining ones.`

# Description of the solution
I saw this project as a little video game. So a basic video game is composed of:
- Game engine to run the game
- World where things happen
- Entities which are actors of the game

In this project, the two last elements are:
- Entity: Rover
- World: Board where the rover can move

At the start, the board goes from 0 0 (bottom-left) to x y (top-right), both included, where x y is the board size given in the input.

The rover can execute three basic instructions:
- Rotate to the left
- Rotate to the right
- Move forward by one unit

The rover can not move outside the Board. For example, if a rover is on the edge and receives a forward move that would take it off the Board, that move is ignored. The rover stays where it is and continues with the next instruction.

# Input structure

```
x y //board size
x y D //x and y are rover position and D is its orientation
AAAA... //A is an instruction (L, R or M)
```
The board size is defined once on the first line. A rover is represented by its initial position and rotation, then followed by a series of instructions. There can be as many rovers as needed.

# Input example
```
10 10
1 1 N
RMMMLMLMRMMRM
5 5 W
RMMLMLMMLRRRMM
```

# Output format

```
x y D //rover final position and rotation
```

Each output line represents a rover's position and rotation after it executed all its instructions.The order of the rovers is the same as in the input.

# Output example
```
4 4 E
4 7 N
```