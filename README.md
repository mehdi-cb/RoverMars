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
I saw this project as a little video game. So a basic video game is composed of:
- Game engine to run the game
- World where things happen
- Entities which are actors of the game

In this project, the two last elements are:
- Entity: Rover
- World: Board where the rover can move

At the start, the board goes from 0 0 (bottom-left) to x y (top-right), both included, where x yy is the board size given in the input.

The rover can execute three basic instructions:
- Rotate to the left
- Rotate to the right
- Move forward by one unit

The rover can not move outside the Board. For example, if a rover is on the edge and receives a forward move that would take it off the Board, that move is ignored. The rover stays where it is and continues with the next instruction.

# Input structure

```
x y //board size
x y D //x and y are rover position and D is its direction
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