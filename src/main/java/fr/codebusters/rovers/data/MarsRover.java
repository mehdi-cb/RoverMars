package fr.codebusters.rovers.data;

public class MarsRover implements Rover {
    private Position position;
    private Direction direction;

    public MarsRover(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void rotateRover(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Position getForwardPosition() {
        return new Position(
                this.position.x() + direction.offsetX,
                this.position.y() + direction.offsetY);
    }

    @Override
    public void moveForward() {
        this.position = getForwardPosition();
    }
}
