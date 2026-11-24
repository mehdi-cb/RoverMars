package fr.codebusters.parser;

import fr.codebusters.exceptions.InvalidInputFormat;
import fr.codebusters.exceptions.InvalidRoverInstruction;
import fr.codebusters.rovers.data.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverInstructionParserTest {
    private final RoverInstructionParser roverInstructionParser = new RoverInstructionParser();

    // Map size parsing tests
    @Test
    public void parseMapSize(){
        Position position = roverInstructionParser.parseBoardSize("5 6");

        assertEquals(5, position.x());
        assertEquals(6, position.y());
    }

    @Test
    public void parseMapSizeTwoDigits(){
        Position position = roverInstructionParser.parseBoardSize("35 48");

        assertEquals(35, position.x());
        assertEquals(48, position.y());
    }

    @Test
    public void parseMapSizeLongDigits(){
        Position position = roverInstructionParser.parseBoardSize("3542 4828384");

        assertEquals(3542, position.x());
        assertEquals(4828384, position.y());
    }

    @Test
    public void parseTwoManyCoordinates(){
        assertThrows(InvalidInputFormat.class, () -> roverInstructionParser.parseBoardSize("2 3 4"));
    }

    @Test
    public void parseMapSizeInvalid(){
        assertThrows(InvalidInputFormat.class, () -> roverInstructionParser.parseBoardSize(""));
    }


    // Rover initialisation parsing tests
    @Test
    public void parseRoverInit(){
        MarsRover rover = roverInstructionParser.parseMarsRoverInit("5 6 N");

        assertEquals(5, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Orientation.NORTH, rover.getOrientation());
    }

    @Test
    public void parseRoverInitMultipleCoordinatesDigits(){
        MarsRover rover = roverInstructionParser.parseMarsRoverInit("1234 56789 N");

        assertEquals(1234, rover.getPosition().x());
        assertEquals(56789, rover.getPosition().y());
        assertEquals(Orientation.NORTH, rover.getOrientation());
    }

    @Test
    public void parseRoverInitEast(){
        MarsRover rover = roverInstructionParser.parseMarsRoverInit("5 6 E");
        assertEquals(5, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Orientation.EAST, rover.getOrientation());
    }

    @Test
    public void parseRoverInitWest(){
        MarsRover rover = roverInstructionParser.parseMarsRoverInit("5 6 W");
        assertEquals(5, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Orientation.WEST, rover.getOrientation());
    }

    @Test
    public void parseRoverInitSouth(){
        MarsRover rover = roverInstructionParser.parseMarsRoverInit("5 6 S");
        assertEquals(5, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Orientation.SOUTH, rover.getOrientation());
    }

    @Test
    public void parseRoverInitInvalid(){
        assertThrows(InvalidInputFormat.class, () -> roverInstructionParser.parseMarsRoverInit(""));
    }

    @Test
    public void parseRoverInitInvalidDirection(){
        assertThrows(InvalidRoverInstruction.class, () -> roverInstructionParser.parseMarsRoverInit("5 5 A"));
    }

    @Test
    public void parseRoverInitTwoManyData(){
        assertThrows(InvalidInputFormat.class, () -> roverInstructionParser.parseMarsRoverInit("1 2 3 N E"));
    }



}
