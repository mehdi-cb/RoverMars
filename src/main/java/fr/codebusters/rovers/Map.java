package fr.codebusters.rovers;

import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.Rover;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int width;
    private final int height;
    private final List<Rover> rovers;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.rovers = new ArrayList<>();
    }

    public void placeNewRover(Rover rover) {
        rovers.add(rover);
    }

    public boolean moveRover(Rover rover, Position newPosition) {
        if (!rovers.contains(rover) || !checkCoordinates(newPosition.x(), newPosition.y())) {
            return false;
        }

        rover.moveForward();
        return true;
    }

    private boolean checkCoordinates(int x, int y) {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }

    public List<Rover> getRovers() {
        return rovers;
    }
}
