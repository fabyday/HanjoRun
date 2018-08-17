package hanjo.hanjorun.GamePamel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import hanjo.hanjorun.Characters.Player;
import hanjo.hanjorun.MainActivity;
import hanjo.hanjorun.R;
import hanjo.hanjorun.TileMap.Background;
import hanjo.hanjorun.TileMap.TileMap;


//실직적으로 화면이 동작한다.
public class GamePanel extends View{

    private TileMap tileMap;
    private Player player;
    private Background background;
    Point displaySize;
    private boolean running;
    public final static int FPS = 60;

    Paint painter;


    public GamePanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        background = new Background(context, (double)1);
        background.setVector((double)5, (double)5);
        tileMap = new TileMap(context);
        player = new Player(context, tileMap, this);
        painter = new Paint();




    }


    public void init()
    {
        tileMap.init();
        player.init();
        setRunning(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        background.draw(canvas, painter);
        tileMap.draw(canvas, painter);
        player.draw(canvas, painter);

    }


    public void update()
    {
        background.update();
        tileMap.update();
        player.update();


    }




    public void setJump()
    {
        player.setJumping();
    }

    public void setClimb(boolean b)
    {
        player.setClimb(b);
    }


    public void setDisplaySize(Point displaySize) //화면에 출력할 오브젝트 크기 설정.
    {
        this.displaySize=displaySize;
        background.setSize(displaySize);
        tileMap.setDisplaySize(displaySize);
        player.setDisplaySize(displaySize);

    }


    public void setRunning(boolean b)
    {

        running=b;

    }

    public boolean getRunning()
    {
        return running;
    }


    public String getScore()
    {
        return player.getScore();
    }

}
