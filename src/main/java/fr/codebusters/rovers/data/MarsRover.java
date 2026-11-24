package fr.codebusters.rovers.data;

public class MarsRover implements Rover {
    private Position position;
    private Orientation orientation;

    public MarsRover(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public void rotateRover(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public Position getForwardPosition() {
        return this.position.getForwardPosition(this.orientation);
    }

    @Override
    public void moveForward(Position newPosition) {
        this.position = newPosition;
    }

    public String describe(){
        return this.position.toString() + " " + this.orientation.toString();
    }
}
