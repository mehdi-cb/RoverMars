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

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Position getForwardPosition() {
        if (direction == Direction.NORTH) {
            return new Position(this.position.x(), this.position.y() + 1);
        }
        if (direction == Direction.SOUTH) {
            return new Position(this.position.x(), this.position.y() - 1);
        }
        if (direction == Direction.EAST) {
            return new Position(this.position.x() + 1, this.position.y());
        }
        if (direction == Direction.WEST) {
            return new Position(this.position.x() - 1, this.position.y());
        }

        return null;
    }
}
