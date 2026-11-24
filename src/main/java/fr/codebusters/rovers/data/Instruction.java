package fr.codebusters.rovers.data;

import fr.codebusters.exceptions.InvalidRoverInstruction;

import java.util.stream.Stream;

public enum Instruction {
    LEFT,
    RIGHT,
    MOVE;

    public static Stream<Instruction> fromInstructionSeries(String instructions){
        return instructions.chars().mapToObj(c -> switch ((char) c) {
            case 'L' -> Instruction.LEFT;
            case 'R' -> Instruction.RIGHT;
            case 'M' -> Instruction.MOVE;
            default -> throw new InvalidRoverInstruction("Invalid rover instruction: " + (char)c);
        });
    }
}
