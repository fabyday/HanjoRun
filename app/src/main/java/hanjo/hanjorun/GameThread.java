package hanjo.hanjorun;

import android.content.Intent;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;

import hanjo.hanjorun.GamePamel.GamePanel;

/**
 * Created by Roh Ji on 2017-11-22.
 */

public class GameThread extends Thread{

    GamePanel gamePanel;
    Handler handler;
    private int targetTime;
    private boolean running;
    private boolean exitFlag;


    public GameThread(Handler handle, GamePanel game)
    {
        handler = handle;
        gamePanel = game;
        exitFlag=false;

    }



    @Override
    public void run() {
        super.run();
        gamePanel.init();
        long startTime;
        long urdTime;
        long waitTime;

        while(gamePanel.getRunning())
        {
            targetTime = 1000 / gamePanel.FPS;
            Message msg = new Message();
            msg.what = 1;
            try{


                startTime = System.nanoTime();
                urdTime = (System.nanoTime() - startTime) / 1000000;
                waitTime = targetTime - urdTime;
                   Thread.sleep(waitTime);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
                handler.sendMessage(msg);

        }
        if(exitFlag==false) {
            Message msg = new Message();
            msg.what = 2;
            handler.sendMessage(msg);
        }

    }


    public void setExit(boolean b)
    {
        exitFlag=true;
    }
}