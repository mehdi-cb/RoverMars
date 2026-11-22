package fr.codebusters.rovers.data;

public enum Direction {
    NORTH('N', 0, 1),
    EAST('E', 1, 0),
    SOUTH('S', 0, -1),
    WEST('W', -1, 0);

    private final char symbol;
    public final int offsetX;
    public final int offsetY;

    Direction(char symbol,  int offsetX, int offsetY) {
        this.symbol = symbol;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    private static final Direction[] values = values();

    public Direction rotateLeft() {
        return values[(this.ordinal() + values.length - 1) % values.length];
    }

    public Direction rotateRight() {
        return values[(this.ordinal() + 1) % values.length];
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    public static Direction fromSymbol(char symbol) {
        for (Direction direction : values()) {
            if (direction.symbol == symbol) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid direction: " + (char)symbol);
    }
}
