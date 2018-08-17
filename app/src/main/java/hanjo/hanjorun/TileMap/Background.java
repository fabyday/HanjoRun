package hanjo.hanjorun.TileMap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;

import hanjo.hanjorun.R;

/**
 * Created by Roh Ji on 2017-11-22.
 */

public class Background {

    Bitmap background;
    double x;
    double y;
    double dx;
    double dy;

    Point displaySize;
    private double moveScale;


    public Background(Context context, double ms)
    {


        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.hanamura);

        moveScale=ms;
        x=0;
        y=0;

    }


    public void setVector(double dx, double dy)
    {
        this.dx=dx;
        this.dy=dy;
    }

    public void update()
    {
        x=(x-dx)%displaySize.x;

    }


    public void setSize( Point displaySize)
    {
        this.displaySize=displaySize;
        background =Bitmap.createScaledBitmap(background, (displaySize.x) , displaySize.y , true);

    }




    public void draw(Canvas canvas, Paint p)
    {

        canvas.drawBitmap(background, (float)x, (float)y, p);
        if(x<0)
            canvas.drawBitmap(background, (float)x+displaySize.x, (float)y, p);
        if(x>0)
            canvas.drawBitmap(background, (float)x-displaySize.x, (float)y, p);
    }


}
