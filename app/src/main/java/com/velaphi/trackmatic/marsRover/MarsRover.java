package com.velaphi.trackmatic.marsRover;

import java.util.ArrayList;
import java.util.List;

public class MarsRover {
    public List<RoverCommand> transform(String input) {
        String[] inputLines = input.split("\n");
        List<RoverCommand> commands = new ArrayList<>(
                (inputLines.length - 1) / 2);
        for (int i = 1; i < inputLines.length; i += 2) {
            RoverCommand command = new RoverCommand();
            String[] inputTokens = inputLines[i].split(" ");
            RoverPosition position = new RoverPosition(
                    Integer.valueOf(inputTokens[0]),
                    Integer.valueOf(inputTokens[1]),
                    Direction.valueOf(inputTokens[2]));
            command.setPosition(position);
            inputTokens = inputLines[i + 1].split("");
            List<Instruction> instructions = new ArrayList<>(
                    inputTokens.length);
            for (String inputToken : inputTokens) {
                if (inputToken.length() != 0) {
                    instructions.add(Instruction.valueOf(inputToken));
                }
                command.setInstructions(instructions);
            }
            commands.add(command);
        }
        return commands;
    }


    public String execute(String input) {
        List<RoverCommand> commands = transform(input);
        StringBuilder output = new StringBuilder(commands.size());
        for (RoverCommand command : commands) {
            output.append(command.execute());
            output.append("\n");
        }
        return output.toString().trim();
    }
}
