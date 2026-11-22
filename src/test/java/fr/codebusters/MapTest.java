package fr.codebusters;

import fr.codebusters.rovers.Map;
import fr.codebusters.rovers.data.Direction;
import fr.codebusters.rovers.data.MarsRover;
import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    private Map map;
    private Rover rover;

    @BeforeEach
    public void setUp() {
        this.map = new Map(10, 10);
        this.rover = new MarsRover(new Position(1, 1), Direction.NORTH);
    }

    @Test
    public void spawnRover(){
        map.placeNewRover(rover);
        assertEquals(1, map.getRovers().size());
        assertEquals(rover, map.getRovers().getFirst());
    }

    @Test
    public void moveRover(){
        map.placeNewRover(rover);
        map.moveRover(rover, rover.getForwardPosition());
        assertEquals(1, rover.getPosition().x());
        assertEquals(2, rover.getPosition().y());
    }

    @ParameterizedTest
    @MethodSource("getEdgeRovers")
    public void moveRoverAtEdge(Rover rover){
        map.placeNewRover(rover);
        Position previousPosition = rover.getPosition();
        map.moveRover(rover, rover.getForwardPosition());
        assertEquals(previousPosition.x(), rover.getPosition().x());
        assertEquals(previousPosition.y(), rover.getPosition().y());
    }

    static Stream<Arguments> getEdgeRovers(){
        return Stream.of(
                Arguments.of(new MarsRover(new Position(10, 10), Direction.EAST)),
                Arguments.of(new MarsRover(new Position(0, 0), Direction.WEST))
        );
    }
}
