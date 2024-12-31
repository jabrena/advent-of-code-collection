package info.jab.aoc.day7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.putoet.resources.ResourceLines;
import info.jab.aoc.Solver;

public class Circuit implements Solver<Integer> {

    private Map<String, Wire> wires;
 
    public Circuit() {
        this.wires = new HashMap<>();
    }

    private boolean tryProcessInstruction(Instruction instruction) {
        try {
            if (instruction.operation() == null || instruction.operation().equals("->")) {
                int value = getValue(instruction.input1());
                setWireValue(instruction.output(), value);
                return true;
            } else if (instruction.operation().equals("NOT")) {
                int value = getValue(instruction.input1());
                setWireValue(instruction.output(), ~value & 0xFFFF);
                return true;
            } else {
                int left = getValue(instruction.input1());
                int right = getValue(instruction.input2());
                
                int result = switch(instruction.operation()) {
                    case "AND" -> left & right;
                    case "OR" -> left | right;
                    case "LSHIFT" -> (left << right) & 0xFFFF;
                    case "RSHIFT" -> (left >>> right) & 0xFFFF;
                    default -> throw new IllegalArgumentException("Unknown operation: " + instruction.operation());
                };
                
                setWireValue(instruction.output(), result);
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private int getValue(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Wire wire = wires.get(input);
            if (wire != null && wire.hasSignal()) {
                return wire.getSignal();
            }
            throw new NoSuchElementException("Wire not found or no signal: " + input);
        }
    }

    private void setWireValue(String wireName, int value) {
        Wire wire = wires.computeIfAbsent(wireName, k -> new Wire(wireName));
        wire.setSignal(value);
    }

    public Integer getWireSignal(String wireName) {
        Wire wire = wires.get(wireName);
        return wire != null && wire.hasSignal() ? wire.getSignal() : null;
    }

    @Override
    public Integer solvePartOne(String fileName) {
        var lines = ResourceLines.list(fileName);
        List<Instruction> instructions = lines.stream()
            .map(Instruction::parse)
            .collect(Collectors.toList());//Mutable list

        while (!instructions.isEmpty()) {
            instructions.removeIf(this::tryProcessInstruction);
        }
        return getWireSignal("a");
    }

    @Override
    public Integer solvePartTwo(String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'solvePartTwo'");
    }
}