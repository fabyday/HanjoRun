package hanjo.hanjorun;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hanjo.hanjorun.Characters.Player;
import hanjo.hanjorun.GamePamel.GamePanel;

/**
 * Created by Roh Ji on 2017-11-16.
 */

public class GameActivity extends Activity{

    ImageButton jump;
    ImageButton climb;
    Button btn;
    GameThread gameThread;
    Display display; //화면 사이즈 측정


    TextView score;
    LinearLayout ScorePanel;
    Button backBtn;

    Canvas canvas;

    GamePanel gamePanel;

    Handler mHandle;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//FullScreen
        setContentView(R.layout.gamepanel);

        mediaPlayer=new MediaPlayer();
        mediaPlayer = mediaPlayer.create(GameActivity.this, R.raw.bgm);
        mediaPlayer.setVolume(0.35F,0.35F);
        mediaPlayer.setLooping(true);

        display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        canvas= new Canvas();
        btn = (Button) findViewById(R.id.menu);
        jump = (ImageButton) findViewById(R.id.jump);
        climb = (ImageButton) findViewById(R.id.climb);
        score=(TextView) findViewById(R.id.Score);
        ScorePanel=(LinearLayout) findViewById(R.id.ScorePanel);
        backBtn=(Button)findViewById(R.id.back);
        gamePanel = (GamePanel) findViewById(R.id.panel);
        gamePanel.setDisplaySize(size);
        gamePanel.init();


        mHandle=new Handler(){
          @Override
          public void handleMessage(Message msg)
          {
            if(msg.what == 1)
            {
                gamePanel.update();
                gamePanel.invalidate();
                gamePanel.draw(canvas);

            }
            if(msg.what==2)
            {
                mediaPlayer.stop();
                mediaPlayer.release();
                gamePanel.setVisibility(View.INVISIBLE);
                jump.setVisibility(View.GONE);
                climb.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);

                score.setTextSize(30);
                score.setText(gamePanel.getScore());
                ScorePanel.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.VISIBLE);



            }


          }
        };

        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamePanel.setJump();
            }
        });

        climb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gamePanel.setClimb(true);
                return false;
            }
        });
        climb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamePanel.setClimb(false);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameThread.setExit(true);
                gamePanel.setRunning(false);
                mediaPlayer.stop();
                onPause();
                finish();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPause();
                finish();
            }
        });

        mediaPlayer.start();
        gameThread = new GameThread(mHandle, gamePanel);
        gameThread.start();

    }


    public void onPause() //게임오버 시 화면 전환 애니메이션 빼기.
    {

        super.onPause();
        overridePendingTransition(0, 0);

    }
    @Override
    public void onBackPressed() //뒤로가기 (임시로 막아놓기)
    {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null)
        mediaPlayer.release();
        mediaPlayer=null;
    }
}
