public class Radio {
    private boolean isOn;
    private int currentChannel;
    private Volume volume;

    public Radio(){
        isOn= false;
        currentChannel = 1;
        volume = new Volume();
    }
    public boolean getIsOn(){
        return isOn;
    }
    public void setIsOn(boolean v){
        isOn = v;
    }
    public void toggleRadio(){
        setIsOn(!isOn);
    }

    public int getCurrentChannel() {
        return currentChannel;
    }
    public void setCurrentChannel(int channel){
        if(isOn){

        if(channel>=1&&channel<=5){
            currentChannel = channel;
        }else{
            System.out.println("WRONG CHANNEL");
        }
        }
    }
    public void increaseChannel() {
        if (isOn) {

            if (currentChannel == 5) {
                setCurrentChannel(1);
            } else {
                setCurrentChannel(currentChannel + 1);
            }
        }
    }
    public void increaseVolume(){
        if(isOn) {

            volume.increaseVolume();
        }
    }
    public void decreaseVolume(){
        if(isOn) {

            volume.decreaseVolume();
        }
    }
    public int getVolume(){
        return volume.getCurrentVolume();
    }
}
