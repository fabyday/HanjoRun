package hanjo.hanjorun.Characters;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Roh Ji on 2017-12-05.
 */

public class HUD {

    private int hp;
    private int meter;
    public HUD()
    {

    }


    public void update(int hp, double meter)
    {
        this.hp=hp;
        this.meter=(int)(meter-300)/100;
    }

    public String getMeter()
    {
        return ("Score : "+meter);
    }
    public String getHp()
    {
        return ("H P : "+hp);
    }
}
