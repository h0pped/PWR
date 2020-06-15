public class FIFO {

    private int page_faults = 0; //count of faults
    private int page_hits = 0;// count of hits

    //local arrays that copy the values of real arrays of frames and pages, to not to work directly
    private int[] local_frames;
    private int[] local_pages;

    public FIFO(int[] pages, int[]frames){ //constructor
        local_pages = new int[pages.length]; //init

        for(int i = 0;i<pages.length;i++){ //copying pages to local array
            local_pages[i] = pages[i];
        }
        local_frames = new int[frames.length]; //init
    }

    public int free_frames(){  //returns count of free frames
    int size = 0;
    for(int i = 0;i<local_frames.length;i++){ //in my representation of algorithms 0 means free frame
        if(local_frames[i]==0){
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
    public int findInsertPlace(){ //find page that wasn't be used for the longest time
        for(int i = 0;i<local_frames.length;i++){  //loop for all frames
            if(local_frames[i]==0){ //if frame is free
                return i; //return index
            }
        }
        return -1; //if there is no free frame
    }
    public void simulate(){ //simulation
        int oldest_index = 0; //index of element which was added  before all
        //the first element will be placed at position 0, so when all frames are occupied, the oldest placed frame will still have index 0

        for(int i = 0;i<local_pages.length;i++){ //loop for all pages
            if(free_frames()>0){ //if there are free frames for next page
                if(findPage(local_pages[i]) == false){ //if page is not in physical memory
                    page_faults++; // faults++
                    local_frames[findInsertPlace()] = local_pages[i]; //add new page into free space

                    //*NOTE
                    //in this algorithm function findInsertPlace used only for finding index when there is free frames
                    //In other algorithms it is replaced by injection of local variable, so function finds insert index
                    //in accordance with the requirements of a particular algorithm

                }
                else{ //if page is already in physical memory
                    page_hits++; //hits++
                }
            }
            else{ //if there is no free frames
                if(findPage(local_pages[i]) == false){ //if page is not in physical memory
                    local_frames[oldest_index] = local_pages[i]; //replacing oldest frame by new page
                    oldest_index++; //now the oldest index is in the next position
                    if(oldest_index>=local_frames.length){ // if the oldest index was the last physical memory index
                        oldest_index=0;
                    }
                    page_faults++; //faults++
                }
                else{
                    page_hits++;//hits++
                }
            }
        }
        System.out.println("FIFO\nPage Hits: "+page_hits+"\nPage Fault: "+page_faults); //results
    }
}
