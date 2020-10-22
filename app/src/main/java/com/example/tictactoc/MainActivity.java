package com.example.tictactoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.gridlayout.widget.GridLayout;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int turnRed = 1;
    int tagTap;
    boolean gameContinue = true;
    int[] array = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] wPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};


    public boolean checkWin() {
        for (int[] winningPosition : wPositions) {

            if (array[winningPosition[0]] == array[winningPosition[1]]
                    && array[winningPosition[1]] == array[winningPosition[2]] && array[winningPosition[0]] != 2) {

                return true;
            }
        }
        return false;
    }

    public boolean checkDraw() {
        for (int item : array) {
            if (item == 2)
                return false;
        }
        return true;
    }

    public void showWin() {
        if (checkWin()) {

            gameContinue = false;
            if (turnRed == 1) {
                Toast.makeText(this, "Green Win", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Red Win", Toast.LENGTH_SHORT).show();
            }
            Button buttonPlay = (Button) findViewById(R.id.buttonAgain);
            buttonPlay.setVisibility(View.VISIBLE);

        } else if (checkDraw()) {
            gameContinue = false;
            Toast.makeText(this, "Draw !!!", Toast.LENGTH_SHORT).show();
            Button buttonPlay = (Button) findViewById(R.id.buttonAgain);
            buttonPlay.setVisibility(View.VISIBLE);
        }

    }

    public void getProcess(ImageView show) {
        array[tagTap] = turnRed;
        if (turnRed == 0) {
            turnRed = 1;
            show.setImageResource(R.drawable.green);
        } else {
            turnRed = 0;
            show.setImageResource(R.drawable.red);
        }
    }

    public void dropIn(View view) {
        ImageView show = (ImageView) view;
        tagTap = Integer.parseInt(show.getTag().toString());

        if (array[tagTap] == 2 && gameContinue) {
            getProcess(show);
            showWin();

        }
    }

    public void Again() {
        turnRed = 1;
        gameContinue = true;
        for (int i = 0; i < 9; i++) {
            array[i] = 2;
        }
    }

    public void playAgain(View view) {
        GridLayout myGridView = (GridLayout) findViewById(R.id.gridLayout);
        Button buttonPlay = (Button) findViewById(R.id.buttonAgain);
        buttonPlay.setVisibility(View.INVISIBLE);
        Again();
        for (int i = 0; i < myGridView.getChildCount(); i++) {
            ImageView child = (ImageView) myGridView.getChildAt(i);
            child.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}