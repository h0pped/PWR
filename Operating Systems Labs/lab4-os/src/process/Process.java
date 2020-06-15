package process;

public class Process {
    public int size = 0;
    public Memory memory;

    public Process(int size, Memory mem) {
        this.size = size;
        this.memory = mem;
    }
}
