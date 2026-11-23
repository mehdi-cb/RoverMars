package fr.codebusters;

import fr.codebusters.parser.FileParser;
import fr.codebusters.rovers.Board;
import fr.codebusters.rovers.data.Instruction;
import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.Rover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GameEngine {
    private final FileParser fileParser;
    private Board board;

    public GameEngine(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public List<String> simulateWorld(String inputFilePath) throws IOException {
        List<String> lines = fileParser.parseFile(inputFilePath);

        Position position = fileParser.parseBoardSize(lines.getFirst());
        this.board = new Board(position.x(), position.y());

        for (int i = 1; i < lines.size(); i+=2) {
            String roverPosition = lines.get(i);
            String roverInstructions = lines.get(i+1);
            Rover rover = fileParser.parseMarsRoverInit(roverPosition);
            board.placeNewRover(rover);
            Stream<Instruction> instructionsStream = fileParser.parseRoverInstructions(roverInstructions);
            instructionsStream.forEach(instruction -> interpretInstruction(rover, instruction));
        }

        return buildRoversSummary(board.getRovers());
    }

    private void interpretInstruction(Rover rover, Instruction instruction){
        switch (instruction){
            case LEFT -> rover.rotateRover(rover.getDirection().rotateLeft());
            case RIGHT -> rover.rotateRover(rover.getDirection().rotateRight());
            case MOVE -> {
                Position forwardPosition = rover.getForwardPosition();
                this.board.moveRover(rover, forwardPosition);
            }
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
