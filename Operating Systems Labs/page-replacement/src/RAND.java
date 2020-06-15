import java.util.Random;

public class RAND {
    private int page_faults = 0; //count of faults
    private int page_hits = 0;// count of hits

    //local arrays that copy the values of real arrays of frames and pages, to not to work directly
    private int[] local_frames;
    private int[] local_pages;

    public RAND(int[] pages, int[] frames) { //constructor
        local_pages = new int[pages.length]; //init

        for (int i = 0; i < pages.length; i++) { //copying pages to local array
            local_pages[i] = pages[i];
        }
        local_frames = new int[frames.length]; //init
    }

    public boolean findPage(int page) { // returns true if we already have this page in physical memory
        for (int i = 0; i < local_frames.length; i++) {
            if (local_frames[i] == page) return true;
        }
        return false;
    }

    public int free_frames() {//returns count of free frames
        int size = 0;
        for (int i = 0; i < local_frames.length; i++) {
            if (local_frames[i] == 0) { //in my representation of algorithms 0 means free frame
                size++;
            }
        }
        return size;
    }

    public void simulate() { //simulation
        Random rand = new Random(); //function of random
        int j = 0; //variable to move through frames when there is free space for new pages
        for (int i = 0; i < local_pages.length; i++) { //loop for pages
            if (findPage(local_pages[i]) == true) { //if page is already in physical memory
                page_hits++; //hits++
            } else { //if page is not in physical memory yet
                if (free_frames() > 0) { //if we have free frame
                    local_frames[j] = local_pages[i]; //add page to physical memory
                    j++;
                } else { //if all frames are occupied
                    int index = rand.nextInt(local_frames.length); //find index to replace by random function
                    local_frames[index] = local_pages[i]; //replacing
                }
                page_faults++; //fault++
            }
        }
        System.out.println("RAND\nPage Hits: " + page_hits + "\nPage Faults: " + page_faults); //results
    }
}
