package fr.codebusters.parser;

import fr.codebusters.exceptions.InvalidInputFormat;
import fr.codebusters.exceptions.InvalidRoverInstruction;
import fr.codebusters.rovers.data.Direction;
import fr.codebusters.rovers.data.Instruction;
import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.MarsRover;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileParserTest {
    private final FileParser fileParser = new FileParser();

    // Map size parsing tests
    @Test
    public void parseMapSize(){
        Position position = fileParser.parseBoardSize("5 6");

        assertEquals(5, position.x());
        assertEquals(6, position.y());
    }

    @Test
    public void parseMapSizeTwoDigits(){
        Position position = fileParser.parseBoardSize("35 48");

        assertEquals(35, position.x());
        assertEquals(48, position.y());
    }

    @Test
    public void parseMapSizeLongDigits(){
        Position position = fileParser.parseBoardSize("3542 4828384");

        assertEquals(3542, position.x());
        assertEquals(4828384, position.y());
    }

    @Test
    public void parseTwoManyCoordinates(){
        assertThrows(InvalidInputFormat.class, () -> fileParser.parseBoardSize("2 3 4"));
    }

    @Test
    public void parseMapSizeInvalid(){
        assertThrows(InvalidInputFormat.class, () -> fileParser.parseBoardSize(""));
    }


    // Rover initialisation parsing tests
    @Test
    public void parseRoverInit(){
        MarsRover rover = fileParser.parseMarsRoverInit("5 6 N");

        assertEquals(5, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Direction.NORTH, rover.getDirection());
    }

    @Test
    public void parseRoverInitMultipleCoordinatesDigits(){
        MarsRover rover = fileParser.parseMarsRoverInit("1234 56789 N");

        assertEquals(1234, rover.getPosition().x());
        assertEquals(56789, rover.getPosition().y());
        assertEquals(Direction.NORTH, rover.getDirection());
    }

    @Test
    public void parseRoverInitEast(){
        MarsRover rover = fileParser.parseMarsRoverInit("5 6 E");
        assertEquals(5, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Direction.EAST, rover.getDirection());
    }

    @Test
    public void parseRoverInitWest(){
        MarsRover rover = fileParser.parseMarsRoverInit("5 6 W");
        assertEquals(5, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Direction.WEST, rover.getDirection());
    }

    @Test
    public void parseRoverInitSouth(){
        MarsRover rover = fileParser.parseMarsRoverInit("5 6 S");
        assertEquals(5, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Direction.SOUTH, rover.getDirection());
    }

    @Test
    public void parseRoverInitInvalid(){
        assertThrows(InvalidInputFormat.class, () -> fileParser.parseMarsRoverInit(""));
    }

    @Test
    public void parseRoverInitInvalidDirection(){
        assertThrows(InvalidRoverInstruction.class, () -> fileParser.parseMarsRoverInit("5 5 A"));
    }

    @Test
    public void parseRoverInitTwoManyData(){
        assertThrows(InvalidInputFormat.class, () -> fileParser.parseMarsRoverInit("1 2 3 N E"));
    }


    // rover instructions parsing tests
    @Test
    public void parseRoverInstruction(){
        List<Instruction> instructionStream = fileParser.parseRoverInstructions("LRM").toList();
        assertEquals(3, instructionStream.size());
        assertEquals(Instruction.LEFT, instructionStream.get(0));
        assertEquals(Instruction.RIGHT, instructionStream.get(1));
        assertEquals(Instruction.MOVE, instructionStream.get(2));
    }

    @Test
    public void parseEmptyRoverInstruction(){
        List<Instruction> instructionStream = fileParser.parseRoverInstructions("").toList();
        assertEquals(0, instructionStream.size());
    }

    @Test
    public void parseRoverInstructionInvalid(){
        assertThrows(InvalidRoverInstruction.class, () -> fileParser.parseRoverInstructions("LRM0").toList());
    }
}
