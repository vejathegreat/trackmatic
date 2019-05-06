package com.velaphi.trackmatic.marsRover;

import java.util.List;

public class RoverCommand {

    private RoverPosition position;
    private List<Instruction> instructions;

    public RoverPosition getPosition() {
        return position;
    }

    public void setPosition(RoverPosition position) {
        this.position = position;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public String execute() {
        for (Instruction instruction : instructions) {
            instruction.execute(position);
        }
        return position.toString();
    }
}
