package fr.codebusters.parser;

import fr.codebusters.exceptions.InvalidInputFormat;
import fr.codebusters.rovers.data.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RoverInstructionParser {
    public List<String> parseFile(String inputFilePath) throws IOException {
        return Files.readAllLines(Paths.get(inputFilePath));
    }

    public Position parseBoardSize(String line) {
        String[] positions = line.split(" ");
        if (positions.length != 2) {
            throw new InvalidInputFormat("Invalid map size line: " + line);
        }

        int width;
        int height;
        try {
            width = Integer.parseInt(positions[0]);
            height = Integer.parseInt(positions[1]);
        } catch (NumberFormatException e) {
            throw new InvalidInputFormat("Invalid map size line: " + line);
        }

        if (width < 0 || height < 0) {
            throw new InvalidInputFormat("Map size must have positive numbers: " + line);
        }
        return new Position(width, height);
    }

    public MarsRover parseMarsRoverInit(String line){
        String[] roverData = line.split(" ");
        if (roverData.length != 3) {
            throw new InvalidInputFormat("Invalid rover init line: " + line);
        }
        Position position;
        try{
            position = new Position(Integer.parseInt(roverData[0]), Integer.parseInt(roverData[1]));
        } catch (NumberFormatException e) {
            throw new InvalidInputFormat("Invalid rover coordinates: " + line);
        }

        if (position.x() < 0 || position.y() < 0){
            throw new InvalidInputFormat("Invalid rover spawn location: " + position);
        }
        if (roverData[2].length() != 1){
            throw new InvalidInputFormat("Invalid rover orientation: " + roverData[2]);
        }

        Orientation orientation = Orientation.fromSymbol(roverData[2].charAt(0));
        return new MarsRover(position, orientation);
    }
}
