package Algorithms;

import process.Memory;
import process.Page;
import process.Process;

import java.util.Arrays;

public class LRU extends Algorithm {
    public LRU() {
        name = "LRU";
    }

    public void local(Memory mem, Process[] processes, Page[] pages) {
        name += "Local";
        faults = 0;
        mem.clear();
        registerParams(mem, processes.length, pages.length);

        for (int i = 0; i < pages.length; i++) {
            Page page = pages[i];
            if (!page.process.memory.contains(page)) {
                if (!page.process.memory.hasEmptyFrame()) {
                    // Gets last used delta for each reference in frames
                    int[] distances = new int[page.process.memory.getFramesNum()];
                    Arrays.fill(distances, Integer.MAX_VALUE);
                    for (int counter = 0; counter < distances.length; counter++) {
                        for (int j = i - 1; j > 0; j--) {
                            if (page.process.memory.frames[counter].equals(pages[j])) {
                                distances[counter] = i - j;
                                break;
                            }
                        }
                    }
                    int index = 0, distance = distances[0]; //was not used for the longest time
                    for (int j = 1; j < distances.length; j++) {
                        if (distance < distances[j]) {
                            distance = distances[j];
                            index = j;
                        }
                    }
                    page.process.memory.remove(index);
                }
                page.process.memory.add(page);
                faults++;
            }
        }
    }

    public void global(Memory phys_mem, Process[] processes, Page[] refs) {
        name += " Global";
        faults = 0;
        phys_mem.clear();
        registerParams(phys_mem, processes.length, refs.length);

        for (int i = 0; i < refs.length; i++) {
            Page ref = refs[i];
            if (!phys_mem.contains(ref)) {
                if (!phys_mem.hasEmptyFrame()) {
                    // Gets last used delta for each reference in frames
                    int[] distances = new int[phys_mem.getFramesNum()];
                    Arrays.fill(distances, Integer.MAX_VALUE);
                    for (int counter = 0; counter < distances.length; counter++) {
                        for (int j = i - 1; j > 0; j--) {
                            if (phys_mem.frames[counter].equals(refs[j])) {
                                distances[counter] = i - j;
                                break;
                            }
                        }
                    }
                    // Selects a reference in frames which has not been
                    //  used for the longest period of time
                    int rem_index = 0, distance = distances[0];
                    for (int j = 1; j < distances.length; j++) {
                        if (distance < distances[j]) {
                            distance = distances[j];
                            rem_index = j;
                        }
                    }
                    Page rem_ref = phys_mem.frames[rem_index];
                    int inner_index = rem_ref.process.memory.getIndex(rem_ref);
                    if (inner_index > 0)
                        rem_ref.process.memory.remove(inner_index);
                    phys_mem.remove(rem_index);
                }
                ref.process.memory.add(ref);
                phys_mem.add(ref);
                faults++;
            }
        }
    }
}
