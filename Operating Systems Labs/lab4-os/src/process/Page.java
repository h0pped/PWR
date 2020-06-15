package process;

public class Page {
    public int address = Integer.MIN_VALUE;
    public Process process;


    public Page(int address, Process p_link) {
        this.address = address;
        this.process = p_link;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Page))
            return false;
        Page other = (Page) obj;
        if (process != null)
            return (this.address == other.address &&
                    this.process == other.process);
        return (this.address == other.address);
    }
}
