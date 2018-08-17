package hanjo.hanjorun.TileMap;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.provider.Settings;
import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import hanjo.hanjorun.Characters.Player;
import hanjo.hanjorun.GamePamel.GamePanel;
import hanjo.hanjorun.R;

/**
 * Created by Roh Ji on 2017-11-20.
 */

public class TileMap {

    Context context;
    int[][][] map;
    private int mapWidth;
    private int mapHeight;
    private int mapNumber;
    int tileSize;
    Tile[][] tile;
    Player player;


    int currentMapNumber; //현재 맵의 위치
    int tempMapNumber;

    //화면에 그릴 위치 시작점.
    int x;
    int y;


    //화면의 최저점 초대점
    int minX;
    int minY;
    int maxX;
    int maxY;


    public TileMap(Context context)
    {
        this.context = context;

        try {


            AssetManager assetText=context.getResources().getAssets(); //asset 폴도의 txt 리소스를 가져온다.
            InputStream text=null;

            try
            {
                text = assetText.open("mapinfo.map");
                BufferedReader br = new BufferedReader(new InputStreamReader(text));
                mapNumber=Integer.parseInt(br.readLine());
                mapWidth=Integer.parseInt(br.readLine());
                mapHeight=Integer.parseInt(br.readLine());
                map = new int[mapNumber][mapHeight][mapWidth];

                String mapFileName="map";
                String limiter= "\\s+";
                for(int i=0; i<mapNumber; i++)
                {

                    String temp = mapFileName.concat(i + ".map");
                    br = new BufferedReader(new InputStreamReader(assetText.open(temp)));
                    for(int j = 0 ; j<mapHeight; j++)
                    {
                      String line= br.readLine();
                      String[] token = line.split(limiter);
                        for(int k = 0; k<mapWidth ; k++)
                        {
                            map[i][j][k] = Integer.parseInt(token[k]);
                        }
                    }
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }



    }

    public void init() //게임 화면 초기화.
    {

        x=0;
        y=26;
        currentMapNumber=0;
    }




    public void roadTiles(Context context)
    {
        tile= new Tile[2][2];
        Bitmap tileSet = BitmapFactory.decodeResource(context.getResources(), R.drawable.nonblock);
        tileSet = Bitmap.createScaledBitmap(tileSet, tileSize , tileSize , true);
      tile[0][0]=new Tile(tileSet,false);
        tileSet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bamboospire);
        tileSet = Bitmap.createScaledBitmap(tileSet, tileSize , tileSize , true);
        tile[0][1]=new Tile(tileSet,false);
        tileSet = BitmapFactory.decodeResource(context.getResources(), R.drawable.block);
        tileSet = Bitmap.createScaledBitmap(tileSet, tileSize , tileSize , true);
        tile[1][0]=new Tile(tileSet,true);
        tileSet = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile);
        tileSet = Bitmap.createScaledBitmap(tileSet, tileSize , tileSize , true);
        tile[1][1]=new Tile(tileSet, true);

    }

    public void update() {}



    public boolean isBlocked(int mapNumber,int row, int col)
    {

        int rc = map[mapNumber][row][col];
        int r, c;

        if(rc==0 || rc==4 || rc == 5 || rc==6)
        {
            r=0;
            c=0;
        }
        else
        {
            r=1;
            c=0;
        }

        return tile[r][c].isBlocked();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int i) {
        x = i;
        if (x < minX) {
            x = minX;
        }
        if (x > maxX) {
            x = maxX;
        }
    }

    public void setY(int i) {
        y = i;
        if (y < minY)
            y = minY;
        if (y > maxY)
            y = maxY;

    }




    public void draw(Canvas canvas, Paint p) //그리는 순서를 위에서 아래로 그리면, map 전환이 일어나도 예외처리해줄 필요가 없음.
    {

        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                int rc = map[0][row][col];
                int r ;
                int c ;

                if(rc==1)
                {
                    r=1;c=0;
                }
                else if(rc==2)
                {
                    r=1; c=1;
                }
                else if(rc==5)
                {
                    r=0;c=1;
                }
                else
                {
                    r=0; c=0;
                }

                if(rc>0)
                    canvas.drawBitmap(tile[r][c].getImage(), x + col * tileSize, y + row * tileSize,p);

            }
        }

    }


    public int getColTile(int x) {




        return x / tileSize;
    }

    public int getRowTile(int y) {

        return y / tileSize;
    }

    public int getTile(int mapNumber,int row, int col) {
        return map[mapNumber][row][col];
    }

    public int getTileSize() {
        return tileSize;
    }


    public void setDisplaySize(Point displaySize)
    {
        this.tileSize=displaySize.x/10;
        minX =displaySize.x-tileSize*mapWidth;
        minY = displaySize.y - tileSize*mapHeight;
        maxX= 0;
        maxY= 0;
        roadTiles(context);
    }


    public int getMapHeight()
    {
        return mapHeight*tileSize;
    }
    public int getMapWidth()
    {
        return mapWidth*tileSize;
    }

}
