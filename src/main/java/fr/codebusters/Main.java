package fr.codebusters;

import fr.codebusters.parser.RoverInstructionParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    static void main(String[] args) {
        if (args.length != 1) {
            LOGGER.error("Wrong number of arguments. Please use 'java -jar RoverMars-1.0-SNAPSHOT.jar <file>");
            System.exit(1);
        }
        String filePath = args[0];
        if (!new File(filePath).exists()) {
            LOGGER.error("File not found. Please check if the file exists.");
            System.exit(1);
        }
        GameEngine gameEngine = new GameEngine(new RoverInstructionParser());

        try {
            String output = gameEngine.simulateWorld(filePath);
            LOGGER.info(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
