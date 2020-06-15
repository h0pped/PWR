public class OPT {
    private int page_faults = 0; //count of faults
    private int page_hits = 0; // count of hits

    //local arrays that copy the values of real arrays of frames and pages, to not to work directly
    private int[] local_frames;
    private int[] local_pages;

    //counter for each frame of how long the frame will not be used
    private int[] not_used_time;


    public OPT(int[] pages, int[]frames){ //constructor
        local_pages = new int[pages.length]; //init

        for(int i = 0;i<pages.length;i++){ //copying pages to local array
            local_pages[i] = pages[i];
        }
        local_frames = new int[frames.length]; //init


        not_used_time = new int[frames.length];  //frame idle time
        for(int i = 0;i<frames.length;i++){ //0 by default for each frame
            not_used_time[i] = 0;
        }
    }
    public int free_frames() { //returns count of free frames
        int size = 0;
        for (int i = 0; i < local_frames.length; i++) {
            if (local_frames[i] == 0) { //in my representation of algorithms 0 means free frame
                size++;
            }
        }
        return size;
    }
    public boolean findPage(int page){ //check do we already have this page in physical memory
        for(int i = 0;i<local_frames.length;i++){
            if(local_frames[i] == page) return true;
        }
        return false;
    }
    public void clearTime(){ //clear supposed idle time for all frames
        for(int i = 0;i<not_used_time.length;i++){
            not_used_time[i]=0;
        }
    }
    public int findInsertPlace(int startIndex){ //find page that wasn't be used for the longest time
        clearTime(); //clearing idle time for all frames
        for(int i = 0;i<local_frames.length;i++){  //loop for all frames
            for(int j = startIndex;j<local_pages.length;j++){ //loop for all pages starts from startIndex
                if(local_frames[i] !=local_pages[j]){  //checking when this frame will be used for the next time
                    not_used_time[i]++; //if not used by next page - idle time+1
                }
                else{ //if usage was found
                    break;
                }
            }
        }

        //and then check which frame will not be used for the longest time
        int max = not_used_time[0];
        int index = 0;
        for(int i = 1;i<not_used_time.length;i++){
            if(max<not_used_time[i]){
                max = not_used_time[i];
                index = i;
            }
        }
        return index;
    }


    public void simulate(){ //simulation of algorithm
        int j = 0; //variable to move through frames when there is free space for new pages
        for(int i = 0;i<local_pages.length;i++){ //loop for all pages
            if(free_frames()>0){ //if we have space in frames for next page
                if(findPage(local_pages[i]) == false){ //if page is not in physical memory yet, then add
                    page_faults++; //increase page faults by 1 cause we add new element
                    local_frames[j] = local_pages[i]; //add page to free frame
                    j++;
                }
                else{ //if page is already in physical memory
                    page_hits++;
                }
            }
            else{ //if we don't have free frames
                if(findPage(local_pages[i]) == false){ //if page is not in physical memory yet, then add
                    int place = findInsertPlace(i); //find which frame will not be used for the longest time
                    local_frames[place] = local_pages[i]; //replacing page
                    page_faults++; //add new element = +1 page fault
                }
                else{ //if page is already in physical memory
                    page_hits++;
                }
            }
        }
        System.out.println("OPT\nPage Hits: "+page_hits+"\nPage Faults: "+page_faults); //results
    }
}
