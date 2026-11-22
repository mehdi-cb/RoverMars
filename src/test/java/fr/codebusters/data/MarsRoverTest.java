package fr.codebusters.data;

import fr.codebusters.rovers.data.Direction;
import fr.codebusters.rovers.data.MarsRover;
import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {
    private Rover rover;

    @BeforeEach
    public void setUp() {
        rover = new MarsRover(new Position(5, 5), Direction.NORTH);
    }

    @Test
    public void roverInitialization() {
        assertEquals(new Position(5,5), rover.getPosition());
        assertEquals(Direction.NORTH, rover.getDirection());
    }

    @Test
    public void rotateRover() {
        rover.rotateRover(Direction.SOUTH);
        assertEquals(Direction.SOUTH, rover.getDirection());
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH, 5, 6",
            "EAST, 6, 5",
            "SOUTH, 5, 4",
            "WEST, 4, 5"
    })
    public void forwardRoverPositionNorth(Direction direction, int expectedX,  int expectedY) {
        rover.rotateRover(direction);
        Position position = rover.getPosition();
        Position forwardPosition = rover.getForwardPosition();
        assertEquals(expectedX, forwardPosition.x());
        assertEquals(expectedY, forwardPosition.y());
    }
}
