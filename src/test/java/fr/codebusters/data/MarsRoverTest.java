package fr.codebusters.data;

import fr.codebusters.rovers.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {
    private Rover rover;

    @BeforeEach
    public void setUp() {
        rover = new MarsRover(new Position(5, 5), Orientation.NORTH);
    }

    @Test
    public void roverInitialization() {
        assertEquals(new Position(5,5), rover.getPosition());
        assertEquals(Orientation.NORTH, rover.getOrientation());
    }

    @Test
    public void rotateRover() {
        rover.rotateRover(Orientation.SOUTH);
        assertEquals(Orientation.SOUTH, rover.getOrientation());
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH, 5, 6",
            "EAST, 6, 5",
            "SOUTH, 5, 4",
            "WEST, 4, 5"
    })
    public void forwardRoverPositionNorth(Orientation orientation, int expectedX, int expectedY) {
        rover.rotateRover(orientation);
        Position forwardPosition = rover.getForwardPosition();
        assertEquals(expectedX, forwardPosition.x());
        assertEquals(expectedY, forwardPosition.y());
    }
}
