package fr.codebusters;

import fr.codebusters.parser.FileParser;
import fr.codebusters.rovers.Map;
import fr.codebusters.rovers.data.Instruction;
import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.Rover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GameEngine {
    private final FileParser fileParser;
    private Map map;

    public GameEngine(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public List<String> simulateWorld(String inputFilePath) throws IOException {
        List<String> lines = fileParser.parseFile(inputFilePath);

        Position position = fileParser.parseMapSize(lines.getFirst());
        this.map = new Map(position.x(), position.y());

        for (int i = 1; i < lines.size(); i+=2) {
            String roverPosition = lines.get(i);
            String roverInstructions = lines.get(i+1);
            Rover rover = fileParser.parseMarsRoverInit(roverPosition);
            map.placeNewRover(rover);
            Stream<Instruction> instructionsStream = fileParser.parseRoverInstructions(roverInstructions);
            instructionsStream.forEach(instruction -> interprateInstruction(rover, instruction));
        }

        return buildRoversSummary(map.getRovers());
    }

    private void interprateInstruction(Rover rover, Instruction instruction){
        if (instruction == Instruction.LEFT){
            rover.rotateRover(rover.getDirection().rotateLeft());
        } else if  (instruction == Instruction.RIGHT){
            rover.rotateRover(rover.getDirection().rotateRight());
        } else if (instruction == Instruction.MOVE){
            Position forwardPosition = rover.getForwardPosition();
            this.map.moveRover(rover, forwardPosition);
        }
    }

    private List<String> buildRoversSummary(List<Rover> rovers){
        List<String> summary = new ArrayList<>();
        for(Rover rover : rovers){
            summary.add(rover.getPosition().toString() + " " + rover.getDirection().toString());
        }
        return summary;
    }
}
