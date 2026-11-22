package fr.codebusters.rovers;

import fr.codebusters.rovers.data.Direction;
import fr.codebusters.rovers.data.Position;
import fr.codebusters.rovers.data.MarsRover;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int width;
    private final int height;
    private final List<MarsRover> rovers;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.rovers = new ArrayList<>();
    }

    public void placeNewRover(MarsRover rover) {
        rovers.add(rover);
    }

    public boolean moveRover(MarsRover rover, Position newPosition) {
        if (!rovers.contains(rover) || !checkCoordinates(newPosition.x(), newPosition.y())) {
            return false;
        }

        rover.setPosition(newPosition);
        return true;
    }

    public boolean rotateRover(MarsRover rover, Direction direction) {
        if (!rovers.contains(rover)) {
            return false;
        }

        rover.setDirection(direction);
        return true;
    }

    private boolean checkCoordinates(int x, int y) {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }

    public List<MarsRover> getRovers() {
        return rovers;
    }
}
