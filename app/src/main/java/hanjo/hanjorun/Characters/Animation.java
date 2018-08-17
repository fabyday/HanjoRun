package hanjo.hanjorun.Characters;

import android.graphics.Bitmap;
import android.provider.Settings;

/**
 * Created by Roh Ji on 2017-12-02.
 */

public class Animation {
    private Bitmap[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    public Animation() {
    }

    public void setFrames(Bitmap[] images) {
        frames = images;
        if (currentFrame >= frames.length)
        {
            currentFrame = 0;

        }
    }
    public void setDelay(long d) {
        delay=d;
    }
    public void update() {
        if(delay==-1)
            return;

        long elapsed=(System.nanoTime()-startTime)/1000000;

        if(elapsed>delay) {
            currentFrame++;
            startTime=System.nanoTime();
        }
        if(currentFrame==frames.length) {
            currentFrame=0;
        }
    }

    public Bitmap getImage() {

        return frames[currentFrame];
    }
}
