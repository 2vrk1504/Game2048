package com.irodos.game2048;

import android.app.Activity;
import android.graphics.Color;
import android.content.*;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vallabh on 7/4/2017.
 */

class Game {
    Box[][] Grid;
    int closed[];
    int num_free;
    Context c;
    Game(Context c){
        this.c = c;
        closed = new int[16];
        Grid = new Box[4][4];
        for(int i = 0; i<4; i++)
            for(int j = 0; j<4; j++)
                Grid[i][j] = new Box(c);
        Grid[0][0].box = (TextView) ((Activity) c).findViewById(R.id.box00);
        Grid[0][1].box = (TextView) ((Activity) c).findViewById(R.id.box01);
        Grid[0][2].box = (TextView) ((Activity) c).findViewById(R.id.box02);
        Grid[0][3].box = (TextView) ((Activity) c).findViewById(R.id.box03);
        Grid[1][0].box = (TextView) ((Activity) c).findViewById(R.id.box10);
        Grid[1][1].box = (TextView) ((Activity) c).findViewById(R.id.box11);
        Grid[1][2].box = (TextView) ((Activity) c).findViewById(R.id.box12);
        Grid[1][3].box = (TextView) ((Activity) c).findViewById(R.id.box13);
        Grid[2][0].box = (TextView) ((Activity) c).findViewById(R.id.box20);
        Grid[2][1].box = (TextView) ((Activity) c).findViewById(R.id.box21);
        Grid[2][2].box = (TextView) ((Activity) c).findViewById(R.id.box22);
        Grid[2][3].box = (TextView) ((Activity) c).findViewById(R.id.box23);
        Grid[3][0].box = (TextView) ((Activity) c).findViewById(R.id.box30);
        Grid[3][1].box = (TextView) ((Activity) c).findViewById(R.id.box31);
        Grid[3][2].box = (TextView) ((Activity) c).findViewById(R.id.box32);
        Grid[3][3].box = (TextView) ((Activity) c).findViewById(R.id.box33);
        num_free = 0;
    }
    void generate_rand_box(boolean e){
       num_free = 0;
       for(int i = 0; i < 4; i++)
           for(int j = 0; j < 4; j++){
               if(!Grid[i][j].isOpen){
                   closed[num_free] = 4*i + j;
                   num_free++;
               }
           }
        if(num_free == 0 && !e){
            Toast.makeText(c, "Game Over. Click on New Game to start one.",
                    Toast.LENGTH_LONG).show();
        }else {
            Log.d("generate_random_box", "num_free=" + num_free + " ");
            int y = (int) (Math.random() * num_free);
            int x = closed[y];
            Log.d("generate_random_box", "y = " + y + " x = " + x + " ");
            int r = x / 4;
            int c = x % 4;
            Grid[r][c].isOpen = true;
            int p = (int) (Math.random() * 1);
            Log.d("generate_random_box", "p =" + p);
            Grid[r][c].val = p * 2 + 2;
            Grid[r][c].box.setText(Grid[r][c].val + "");
            Grid[r][c].setColour();
        }
    }
    void up(){
        boolean enterLoop = false;
        for(int c = 0; c < 4; c++){
            for(int r = 1; r < 4; r++) {
                if(Grid[r][c].isOpen) {
                    int i = r;
                    while(i != 0 && (!Grid[i - 1][c].isOpen || Grid[i-1][c].val==Grid[i][c].val) ) {
                        enterLoop = true;
                        if(Grid[i-1][c].val==Grid[i][c].val){
                            Grid[i-1][c].inc();
                            Grid[i-1][c].box.setText(Grid[i - 1][c].val + "");
                            Grid[i-1][c].setColour();
                            Grid[i][c].isOpen = false;
                            Grid[i][c].val = 0;
                            Grid[i][c].box.setText("");
                            Grid[i][c].box.setBackgroundColor(Color.rgb(204, 204, 179));
                            break;
                        }
                        else {
                            Grid[i - 1][c].val = Grid[i][c].val;
                            Grid[i-1][c].setColour();
                            Grid[i-1][c].box.setText(Grid[i - 1][c].val + "");
                            Grid[i-1][c].isOpen = true;
                            Grid[i][c].isOpen = false;
                            Grid[i][c].val = 0;
                            Grid[i][c].box.setText("");
                            Grid[i][c].box.setBackgroundColor(Color.rgb(204, 204, 179));
                        }
                        i--;
                    }
                }
            }
        }
        if(enterLoop)
            generate_rand_box(enterLoop);
    }
    void down(){
        boolean enterLoop = false;
        for(int c = 0; c < 4; c++){
            for(int r = 2; r >= 0; r--) {
                if(Grid[r][c].isOpen) {
                    int i = r;
                    while(i != 3 && (!Grid[i + 1][c].isOpen || Grid[i+1][c].val==Grid[i][c].val) ) {
                        enterLoop = true;
                        if(Grid[i+1][c].val==Grid[i][c].val){
                            Grid[i+1][c].inc();
                            Grid[i+1][c].box.setText(Grid[i + 1][c].val + "");
                            Grid[i+1][c].setColour();
                            Grid[i][c].isOpen = false;
                            Grid[i][c].val = 0;
                            Grid[i][c].box.setText("");
                            Grid[i][c].box.setBackgroundColor(Color.rgb(204, 204, 179));
                            break;
                        }
                        else {
                            Grid[i + 1][c].val = Grid[i][c].val;
                            Grid[i+1][c].setColour();
                            Grid[i+1][c].box.setText(Grid[i][c].val + "");
                            Grid[i+1][c].isOpen = true;
                            Grid[i][c].isOpen = false;
                            Grid[i][c].val = 0;
                            Grid[i][c].box.setText("");
                            Grid[i][c].box.setBackgroundColor(Color.rgb(204, 204, 179));
                        }
                        i++;
                    }
                }
            }
        }
        if(enterLoop)
            generate_rand_box(enterLoop);
    }
    void right(){
        boolean enterLoop = false;
        for(int r = 0; r < 4; r++){
            for(int c = 2; c >= 0; c--) {
                if(Grid[r][c].isOpen) {
                    int i = c;
                    while(i != 3 && (!Grid[r][i+1].isOpen || Grid[r][i+1].val==Grid[r][i].val) ) {
                        enterLoop = true;
                        if(Grid[r][i+1].val==Grid[r][i].val){
                            Grid[r][i+1].inc();
                            Grid[r][i+1].box.setText(Grid[r][i + 1].val + "");
                            Grid[r][i+1].setColour();
                            Grid[r][i].isOpen = false;
                            Grid[r][i].val = 0;
                            Grid[i][c].box.setText("");
                            Grid[i][c].box.setBackgroundColor(Color.rgb(204, 204, 179));
                            break;
                        }
                        else {
                            Grid[r][i+1].val = Grid[r][i].val;
                            Grid[r][i+1].box.setText(Grid[r][i + 1].val + "");
                            Grid[r][i+1].setColour();
                            Grid[r][i+1].isOpen = true;
                            Grid[r][i].isOpen = false;
                            Grid[r][i].val = 0;
                            Grid[r][i].box.setText("");
                            Grid[r][i].box.setBackgroundColor(Color.rgb(204, 204, 179));
                        }
                        i++;
                    }
                }
            }
        }
        if(enterLoop)
            generate_rand_box(enterLoop);
    }
    void left(){
        boolean enterLoop = false;
        for(int r = 0; r < 4; r++){
            for(int c = 1; c < 4; c++) {
                if(Grid[r][c].isOpen) {
                    int i = c;
                    while(i != 0 && (!Grid[r][i-1].isOpen || Grid[r][i-1].val==Grid[r][i].val) ) {
                        enterLoop = true;
                        if(Grid[r][i-1].val==Grid[r][i].val){
                            Grid[r][i-1].inc();
                            Grid[r][i-1].box.setText(Grid[r][i - 1].val + "");
                            Grid[r][i-1].setColour();
                            Grid[r][i].isOpen = false;
                            Grid[r][i].val = 0;
                            Grid[r][i].box.setText("");
                            Grid[r][i].box.setBackgroundColor(Color.rgb(204, 204, 179));
                            break;
                        }
                        else {
                            Grid[r][i-1].val = Grid[r][i].val;
                            Grid[r][i-1].box.setText(Grid[r][i - 1].val + "");
                            Grid[r][i-1].setColour();
                            Grid[r][i-1].isOpen = true;
                            Grid[r][i].isOpen = false;
                            Grid[r][i].val = 0;
                            Grid[r][i].box.setText("");
                            Grid[r][i].box.setBackgroundColor(Color.rgb(204, 204, 179));
                        }
                        i--;                        
                    }
                }
            }
        }
        if(enterLoop)
            generate_rand_box(enterLoop);
    }
}

