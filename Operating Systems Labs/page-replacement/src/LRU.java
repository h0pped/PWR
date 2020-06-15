public class LRU {

    private int page_faults = 0; //count of faults
    private int page_hits = 0; //count of hits

    //local arrays that copy the values of real arrays of frames and pages, to not to work directly
    private int[] local_frames;
    private int[] local_pages;

    //array which stores idle time for each frame
    private int[] not_used_time;

    public LRU(int[] pages, int[] frames) { //constructor
        local_pages = new int[pages.length]; //init

        for (int i = 0; i < pages.length; i++) { //copying pages to local array
            local_pages[i] = pages[i];
        }
        local_frames = new int[frames.length]; //init


        not_used_time = new int[frames.length];  //frame idle time
        for (int i = 0; i < frames.length; i++) { //0 by default
            not_used_time[i] = 0;
        }
    }

    public int free_frames() { // do we have free space in physical memory
        int size = 0;
        for (int i = 0; i < local_frames.length; i++) {
            if (local_frames[i] == 0) {
                size++;
            }
        }
        return size;
    }

    public boolean findPage(int page) { //do we already have this page in physical memory
        for (int i = 0; i < local_frames.length; i++) {
            if (local_frames[i] == page) return true;
        }
        return false;
    }

    public int findInsertPlace() { //find page that wasn't used for the longest time
        int max = not_used_time[0];
        int index = 0;
        for (int i = 1; i < not_used_time.length; i++) {
            if (max < not_used_time[i]) {
                max = not_used_time[i];
                index = i;
            }
        }
        return index;
    }

    public void increaseIdleTime() { //increase idle time for all frames by 1
        for (int i = 0; i < not_used_time.length; i++) {
            not_used_time[i]++;
        }
    }
    public int getIndex(int page){ //get index of page
        for(int i = 0;i<local_frames.length;i++){
            if(local_frames[i]==page){
                return i;
            }
        }
        return -1; //-1 if wasn't found
    }

    public void simulate(){ //simulation
        int j = 0; //variable to move through frames when there is free space for new pages
        for(int i = 0;i<local_pages.length;i++){//loop for all pages
            if(free_frames()>0){//if we have space in frame for next page
                if(findPage(local_pages[i]) == false){ //if page is not in physical memory yet, then add
                    local_frames[j] = local_pages[i]; //add new page
                    increaseIdleTime();// increase idle time for all frames cause they weren't used
                    not_used_time[j]=0; //set idle time for replaced frame to 0
                    page_faults++; //faults++
                    j++;
                }
                else{ //if page is already in physical memory
                    page_hits++; //hits++
                    increaseIdleTime(); // increase idle time for all frames cause they weren't used
                    not_used_time[j] = 0; //set idle time for found frame to 0
                }
            }
            else{ //if we don't have free frame for next page
                if(findPage(local_pages[i]) == false){ //if page wasn't found in physical memory
                    int place = findInsertPlace(); //find page with biggest idle time
                    local_frames[place] = local_pages[i]; //replace frame
                    increaseIdleTime(); // increase idle time for all frames cause they weren't used
                    not_used_time[place]=0; //set idle time for replaced frame to 0
                    page_faults++; //faults++
                }
                else{ //if page was found in physical memory
                    page_hits++; //hits++
                    increaseIdleTime(); //increase idle time for all frames cause they weren't used
                    not_used_time[getIndex(local_pages[i])] = 0; //set idle time for found frame to 0
                }
            }
        }
        System.out.println("LRU\nPage Hits: "+page_hits+"\nPage Faults: "+page_faults); //results
    }
}
