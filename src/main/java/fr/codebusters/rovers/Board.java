package fr.codebusters.rovers;

import fr.codebusters.exceptions.InvalidInputFormat;
import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.Rover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);

    private final int width;
    private final int height;
    private final List<Rover> rovers;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.rovers = new ArrayList<>();
    }

    public void placeNewRover(Rover rover) {
        if (!checkCoordinates(rover.getPosition().x(), rover.getPosition().y())) {
            throw new InvalidInputFormat("Invalid coordinates: " + rover.getPosition());
        }
        rovers.add(rover);
    }

    public void moveRover(Rover rover, Position newPosition) {
        if (!checkCoordinates(newPosition.x(), newPosition.y())) {
            LOGGER.warn("Rover cannot move outside the Board");
            return;
        }

        rover.moveForward(newPosition);
    }

    private boolean checkCoordinates(int x, int y) {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }

    public List<Rover> getRovers() {
        return rovers;
    }
}
