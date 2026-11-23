package fr.codebusters.parser;

import fr.codebusters.exceptions.InvalidInputFormat;
import fr.codebusters.exceptions.InvalidRoverInstruction;
import fr.codebusters.rovers.data.Direction;
import fr.codebusters.rovers.data.Instruction;
import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.MarsRover;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileParser {
    public List<String> parseFile(String inputFilePath) throws IOException {
        return Files.readAllLines(Paths.get(inputFilePath));
    }

    public Position parseBoardSize(String line) {
        String[] positions = line.split(" ");
        if (positions.length != 2) {
            throw new InvalidInputFormat("Invalid map size line: " + line);
        }

        try {
            return new Position(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
        } catch (NumberFormatException e) {
            throw new InvalidInputFormat("Invalid map size line: " + line);
        }
    }

    public MarsRover parseMarsRoverInit(String line){
        String[] roverData = line.split(" ");
        if (roverData.length != 3) {
            throw new InvalidInputFormat("Invalid rover init line: " + line);
        }
        Position position = new Position(Integer.parseInt(roverData[0]), Integer.parseInt(roverData[1]));
        if (position.x() < 0 || position.y() < 0){
            throw new InvalidInputFormat("Invalid rover spawn location: " + position);
        }
        Direction direction = Direction.fromSymbol(roverData[2].charAt(0));
        return new MarsRover(position, direction);
    }

    public Stream<Instruction> parseRoverInstructions(String instructions){
        return instructions.chars().mapToObj(c -> switch ((char) c) {
            case 'L' -> Instruction.LEFT;
            case 'R' -> Instruction.RIGHT;
            case 'M' -> Instruction.MOVE;
            default -> throw new InvalidRoverInstruction("Invalid rover instruction: " + (char)c);
        });
    }
}
