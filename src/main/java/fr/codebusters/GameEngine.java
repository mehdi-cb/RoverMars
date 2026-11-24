package fr.codebusters;

import fr.codebusters.parser.RoverInstructionParser;
import fr.codebusters.rovers.Board;
import fr.codebusters.rovers.data.Instruction;
import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.Rover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);

    private final RoverInstructionParser roverInstructionParser;
    private Board board;

    public GameEngine(RoverInstructionParser roverInstructionParser) {
        this.roverInstructionParser = roverInstructionParser;
    }

    public String simulateWorld(String inputFilePath) throws IOException {
        List<String> lines = roverInstructionParser.parseFile(inputFilePath);

        Position boardSize = roverInstructionParser.parseBoardSize(lines.getFirst());
        this.board = new Board(boardSize.x(), boardSize.y());

        for (int i = 1; i < lines.size() - 1; i+=2) {
            LOGGER.info("A new rover landed on Mars !");
            String roverPosition = lines.get(i);
            String roverInstructions = lines.get(i+1);
            Rover rover = roverInstructionParser.parseMarsRoverInit(roverPosition);
            board.placeNewRover(rover);
            Stream<Instruction> instructionsStream = Instruction.fromInstructionSeries(roverInstructions);
            instructionsStream.forEach(instruction -> interpretInstruction(rover, instruction));
        }

        return buildRoversSummary(board.getRovers());
    }

    private void interpretInstruction(Rover rover, Instruction instruction){
        switch (instruction){
            case LEFT -> rover.rotateRover(rover.getOrientation().rotateLeft());
            case RIGHT -> rover.rotateRover(rover.getOrientation().rotateRight());
            case MOVE -> {
                Position forwardPosition = rover.getForwardPosition();
                this.board.moveRover(rover, forwardPosition);
            }
        }
    }

    private String buildRoversSummary(List<Rover> rovers){
        return rovers.stream().map(Rover::describe).collect(Collectors.joining("\n"));
    }
}
