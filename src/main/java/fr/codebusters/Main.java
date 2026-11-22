package fr.codebusters;

import fr.codebusters.parser.FileParser;

import java.io.IOException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar Main.jar <file>");
            System.exit(1);
        }
        String filePath = args[0];
        GameEngine gameEngine = new GameEngine(new FileParser());

        try {
            List<String> output = gameEngine.simulateWorld(filePath);
            output.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
