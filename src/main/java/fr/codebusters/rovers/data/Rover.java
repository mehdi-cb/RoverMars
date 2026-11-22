package fr.codebusters.rovers.data;

public interface Rover {
    Position getPosition();
    Direction getDirection();
    Position getForwardPosition();
    void moveForward();
    void rotateRover(Direction direction);
}
