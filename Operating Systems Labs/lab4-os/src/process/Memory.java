package process;

public class Memory {

    public Page[] frames;

    public Memory(int framesNum) {
        frames = new Page[Math.max(1, framesNum)];
    }

    public int getFramesNum() {
        return this.frames.length;
    }


    public boolean contains(Page ref) {
        for (Page element : frames) {
            if (ref.equals(element))
                return true;
        }
        return false;
    }

    public void add(Page ref) {
        if (hasEmptyFrame()) {
            for (int i = 0; i < frames.length; i++) {
                if (frames[i] == null || frames[i].address == Integer.MIN_VALUE) {
                    frames[i] = ref;
                    break;
                }
            }
        }
    }


    public void remove(int index) {
        frames[index] = null;
    }

    public int getIndex(Page ref) {
        for (int i = 0; i < frames.length; i++)
            if (ref.equals(frames[i]))
                return i;
        return -1;
    }

    public boolean hasEmptyFrame() {
        for (Page element : frames)
            if (element == null || element.address == Integer.MIN_VALUE)
                return true;
        return false;
    }

    public void clear() {
        for (int i = 0; i < frames.length; i++) {
            frames[i] = null;
        }
    }

    public String toString() {
        String output = "[";
        for (Page frame : frames) {
            if (frame == null || frame.address == Integer.MIN_VALUE)
                output += "/, ";
            else
                output += frame.address + ", ";
        }
        output = output.substring(0, output.length() - 2) + "]";
        return output;
    }
}

