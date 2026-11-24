package fr.codebusters.rovers.data;

public interface Rover {
    Position getPosition();
    Orientation getOrientation();
    Position getForwardPosition();
    void moveForward(Position newPosition);
    void rotateRover(Orientation orientation);
    String describe();
}
