package com.puranjan.x_omasters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //0-X
    //1-O
    //2-null
    int activePlayer = 0;
    int[] GameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int [][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8},{0,4,8},{2,4,6}};

    //method for on click
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
            return;
        }
        if (GameState[tappedImage]==2) {
            GameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's TURM - TAP TO PLAY");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's TURM - TAP TO PLAY");
            }
            img.animate().translationYBy(1000f).setDuration(280);
        }

        //check if any player has own
        for (int[] winPosition: winPositions){
            if (GameState[winPosition[0]]==GameState[winPosition[1]] &&
                    GameState[winPosition[1]]==GameState[winPosition[2]] && GameState[winPosition[0]]!=2){
                //somebody won but who..?
                String winnerStr;
                gameActive = false;
                if (GameState[winPosition[0]]==0){
                    winnerStr = "X HAS WON..";
                }
                else {
                    winnerStr = "O HAS WON..";
                }
                //updating the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        //for match draw
        boolean emptySQ = false;
        for (int sqState: GameState){
            if (sqState==2){
                emptySQ = true;
                break;
            }
        }
        if (!emptySQ && gameActive){
            gameActive = false;
            String draw;
            draw = "MATCH DRAW..";
            TextView status = findViewById(R.id.status);
            status.setText(draw);
        }


    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i<GameState.length; i++){
            GameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's TURM - TAP TO PLAY");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}