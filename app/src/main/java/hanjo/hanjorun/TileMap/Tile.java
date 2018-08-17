package hanjo.hanjorun.TileMap;

import android.graphics.Bitmap;

/**
 * Created by Roh Ji on 2017-11-20.
 */

public class Tile {
    private Bitmap tile;
    private boolean isBlock;
    public Tile(Bitmap tile, boolean isBlock)
    {
        this.tile = tile;
        this.isBlock= isBlock;
    }



    public boolean isBlocked()
    {
        return isBlock;
    }

    public Bitmap getImage()
    {
        return tile;
    }


}
