package fr.codebusters.rovers.data;

import fr.codebusters.exceptions.InvalidRoverInstruction;

import java.util.Arrays;

public enum Orientation {
    NORTH('N', 0, 1),
    EAST('E', 1, 0),
    SOUTH('S', 0, -1),
    WEST('W', -1, 0);

    private final char symbol;
    private final int offsetX;
    private final int offsetY;

    Orientation(char symbol, int offsetX, int offsetY) {
        this.symbol = symbol;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public Orientation rotateLeft() {
        return switch (this){
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        };
    }

    public Orientation rotateRight() {
        return switch (this){
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    public static Orientation fromSymbol(char symbol) {
        return Arrays.stream(values())
                .filter(d -> d.symbol == symbol)
                .findFirst()
                .orElseThrow(() -> new InvalidRoverInstruction("Invalid direction: " + symbol));
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
}
