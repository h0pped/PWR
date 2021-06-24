import org.junit.Assert;
import org.junit.Test;
public class tests {
    @Test
    public void testVolume() {
        Radio radio = new Radio();
        for(int i = 0; i < 200; i++){
            radio.decreaseVolume();
        }
        Assert.assertEquals(radio.getVolume(), 0);
        for(int i = 0; i < 200; i++){
            radio.increaseVolume();
        }
        Assert.assertEquals(radio.getVolume(), 0);
        radio.toggleRadio();
        for(int i = 0; i < 200; i++){
            radio.decreaseVolume();
        }
        Assert.assertEquals(radio.getVolume(), 0);
        for(int i = 0; i < 200; i++){
            radio.increaseVolume();
        }
        Assert.assertEquals(radio.getVolume(), 100);
    }
    @Test
    public void testChannel() {
        Radio radio = new Radio();
        //test when off
        radio.decreaseVolume();
        Assert.assertEquals(radio.getCurrentChannel(), 1);
        radio.increaseChannel();
        Assert.assertEquals(radio.getCurrentChannel(), 1);

        radio.toggleRadio();
        radio.increaseChannel();
        radio.increaseChannel();
        radio.increaseChannel();
        radio.increaseChannel();
        Assert.assertEquals(radio.getCurrentChannel(), 5);
        radio.increaseChannel();
        Assert.assertEquals(radio.getCurrentChannel(), 1);
    }
} 