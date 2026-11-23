package fr.codebusters;

import fr.codebusters.rovers.Board;
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

public class BoardTest {
    private Board board;
    private Rover rover;

    @BeforeEach
    public void setUp() {
        this.board = new Board(10, 10);
        this.rover = new MarsRover(new Position(1, 1), Direction.NORTH);
    }

    @Test
    public void spawnRover(){
        board.placeNewRover(rover);
        assertEquals(1, board.getRovers().size());
        assertEquals(rover, board.getRovers().getFirst());
    }

    @Test
    public void moveRover(){
        board.placeNewRover(rover);
        board.moveRover(rover, rover.getForwardPosition());
        assertEquals(1, rover.getPosition().x());
        assertEquals(2, rover.getPosition().y());
    }

    @ParameterizedTest
    @MethodSource("getEdgeRovers")
    public void moveRoverAtEdge(Rover rover){
        board.placeNewRover(rover);
        Position previousPosition = rover.getPosition();
        board.moveRover(rover, rover.getForwardPosition());
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
