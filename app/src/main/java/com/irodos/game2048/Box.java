package com.irodos.game2048;

/**
 * Created by Vallabh on 7/4/2017.
 */
import android.widget.TextView;
import android.content.*;
import android.graphics.Color;

public class Box{
    public int val;
    public int colour;
    public boolean isOpen;
    public TextView box;
    Box(Context c){
        isOpen = false;
        val = 0;
        colour = -1;
        box = new TextView(c);
    }
    public void setColour(){
        switch(val){
            case 2: {box.setBackgroundColor(Color.rgb(242, 255, 215)); box.setTextColor(Color.BLACK); break;}
            case 4: {box.setBackgroundColor(Color.rgb(240, 247, 162)); box.setTextColor(Color.BLACK); break;}
            case 8: {box.setBackgroundColor(Color.rgb(255, 153, 0)); box.setTextColor(Color.WHITE); break;}
            case 16:{box.setBackgroundColor(Color.rgb(255, 128, 0)); box.setTextColor(Color.WHITE); break;}
            case 32:{box.setBackgroundColor(Color.rgb(255, 70, 74));box.setTextColor(Color.WHITE); break;}
            case 64:{box.setBackgroundColor(Color.rgb(255, 0, 0));box.setTextColor(Color.WHITE); break;}
            case 128:{box.setBackgroundColor(Color.rgb(255, 239, 4));box.setTextColor(Color.WHITE); break;}
            case 256:{box.setBackgroundColor(Color.rgb(255, 221, 4));box.setTextColor(Color.WHITE); break;}
            case 512:{box.setBackgroundColor(Color.rgb(255, 214, 4));box.setTextColor(Color.WHITE); break;}
            case 1024:{box.setBackgroundColor(Color.rgb(255, 210, 4));box.setTextColor(Color.WHITE); break;}
            case 2048:{box.setBackgroundColor(Color.rgb(255, 208, 4));box.setTextColor(Color.WHITE); break;}
        }
    }
    public void init_Box() {
        isOpen = true;
        int random = (int) (Math.random() + 1);
        if (random == 0) {
            val = 2;
            colour = 0;
        } else {
            val = 4;
            colour = 1;
        }
    }

    public void inc(){
        val *= 2;
    }
}


