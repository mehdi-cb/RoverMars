package fr.codebusters.rovers.data;

public record Position(int x, int y) {
    @Override
    public String toString() {
        return x + " " + y;
    }

    public Position getForwardPosition(Orientation orientation) {
        return new Position(x + orientation.getOffsetX(), y + orientation.getOffsetY());
    }
}
