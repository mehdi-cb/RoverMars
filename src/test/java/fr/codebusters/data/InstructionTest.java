package fr.codebusters.data;

import fr.codebusters.exceptions.InvalidRoverInstruction;
import fr.codebusters.rovers.data.Instruction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InstructionTest {
    @Test
    public void parseRoverInstruction(){
        List<Instruction> instructionStream = Instruction.fromInstructionSeries("LRM").toList();
        assertEquals(3, instructionStream.size());
        assertEquals(Instruction.LEFT, instructionStream.get(0));
        assertEquals(Instruction.RIGHT, instructionStream.get(1));
        assertEquals(Instruction.MOVE, instructionStream.get(2));
    }

    @Test
    public void parseEmptyRoverInstruction(){
        List<Instruction> instructionStream = Instruction.fromInstructionSeries("").toList();
        assertEquals(0, instructionStream.size());
    }

    @Test
    public void parseRoverInstructionInvalid(){
        assertThrows(InvalidRoverInstruction.class, () -> Instruction.fromInstructionSeries("LRM0").toList());
    }
}
