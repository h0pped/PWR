package Algorithms;

import process.Memory;

public abstract class Algorithm {
    public String name;
    public int physical_memory = 0;
    public int length = 0;
    public int processes_number = 0;
    public int faults = 0;

    protected void registerParams(Memory mem, int processes, int virtual) {
        processes_number = processes;
        physical_memory = mem.getFramesNum();
        length = virtual;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("\n"+name + " result");
        output.append("\nProcesses number:" + processes_number + "\n");
        output.append("Memory size:" + physical_memory + "\n");
        output.append("Virtual Memory:" + length + "\n");
        output.append("Faults:" + faults + "\n");
        return output.toString();
    }
}
