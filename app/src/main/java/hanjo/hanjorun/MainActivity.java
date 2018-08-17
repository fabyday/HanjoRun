package hanjo.hanjorun;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;



public class MainActivity extends AppCompatActivity {

    ImageButton startBtn;
    ImageButton exitBtn;
    SoundPool soundPool;
    int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//FullScreen
        setContentView(R.layout.activity_main);
        soundPool=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
       a=soundPool.load(MainActivity.this, R.raw.sentence, 1);

        startBtn= (ImageButton) findViewById(R.id.startBtn);
        exitBtn=(ImageButton) findViewById(R.id.exitBtn);


        startBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        startBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                soundPool.play(a,1.0F,1.0F,1,0,-1.0F);
                Intent changeView = new Intent(MainActivity.this, GameActivity.class);
                startActivity(changeView); //뷰의 전환
                overridePendingTransition(0,0);
            }
        });



        exitBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false;
            }
        });

        exitBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });


        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }






}
