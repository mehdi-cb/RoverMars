package fr.codebusters.rovers.data;

public enum Direction {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    private final char symbol;

    Direction(char symbol){
        this.symbol = symbol;
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
