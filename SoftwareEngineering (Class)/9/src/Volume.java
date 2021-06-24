public class Volume {
    private int currentVolume;

    public Volume(){
        currentVolume = 0;
    }
    public int getCurrentVolume(){
        return currentVolume;
    }
    public void setCurrentVolume(int vol){
        if(vol>=0&&vol<=100){
            currentVolume = vol;
        }
        else{
            System.out.println("WRONG VOLUME");
        }
    }
    public void increaseVolume(){
        setCurrentVolume(currentVolume+1);
    }
    public void decreaseVolume(){
        setCurrentVolume(currentVolume-1);
    }

}
