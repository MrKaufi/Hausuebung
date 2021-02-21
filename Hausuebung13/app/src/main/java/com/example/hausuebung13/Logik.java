package com.example.hausuebung13;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Logik {

    int zuege = 9;
    String ox = "O";
    int[][] fieldStatus = new int[3][3];

    public void handleInput(int i, int i1, Context context) {
        if (fieldStatus[i - 1][i1 - 1] == 0) {
            if (ox.equals("O")) {
                fieldStatus[i - 1][i1 - 1] = 1;
                ox = "X";
            } else {
                fieldStatus[i - 1][i1 - 1] = -1;
                ox = "O";
            }
        }
        if (checkFinished()) {
            if (ox.equals("O")) {
                Toast.makeText(context, ("X hat gewonnen"), Toast.LENGTH_LONG).show();
            } else Toast.makeText(context, ("O hat gewonnen"), Toast.LENGTH_LONG).show();
            zuege = 10;
        } else if (checkDraw()) {
            Toast.makeText(context, ("unentschieden"), Toast.LENGTH_LONG).show();
            zuege = 10;
        }

        zuege--;
    }

    public boolean checkFinished() {
        return (Math.abs(fieldStatus[0][0] + fieldStatus[0][1] + fieldStatus[0][2]) == 3
                || Math.abs(fieldStatus[1][0] + fieldStatus[1][1] + fieldStatus[1][2]) == 3
                || Math.abs(fieldStatus[2][0] + fieldStatus[2][1] + fieldStatus[2][2]) == 3
                || Math.abs(fieldStatus[0][0] + fieldStatus[1][0] + fieldStatus[2][0]) == 3
                || Math.abs(fieldStatus[0][1] + fieldStatus[1][1] + fieldStatus[2][1]) == 3
                || Math.abs(fieldStatus[0][2] + fieldStatus[1][2] + fieldStatus[2][2]) == 3
                || Math.abs(fieldStatus[0][0] + fieldStatus[1][1] + fieldStatus[2][2]) == 3
                || Math.abs(fieldStatus[0][2] + fieldStatus[1][1] + fieldStatus[2][0]) == 3);
    }

    public boolean checkDraw() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (fieldStatus[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void onClick(View view, Button[] felder, Context context) {
        switch (view.getId()) {
            case R.id.feld_11:
                felder[0].setText(ox);
                handleInput(1, 1, context);
                break;
            case R.id.feld_12:
                felder[1].setText(ox);
                handleInput(1, 2, context);
                break;
            case R.id.feld_13:
                felder[2].setText(ox);
                handleInput(1, 3, context);
                break;
            case R.id.feld_21:
                felder[3].setText(ox);
                handleInput(2, 1, context);
                break;
            case R.id.feld_22:
                felder[4].setText(ox);
                handleInput(2, 2, context);
                break;
            case R.id.feld_23:
                felder[5].setText(ox);
                handleInput(2, 3, context);
                break;
            case R.id.feld_31:
                felder[6].setText(ox);
                handleInput(3, 1, context);
                break;
            case R.id.feld_32:
                felder[7].setText(ox);
                handleInput(3, 2, context);
                break;
            case R.id.feld_33:
                felder[8].setText(ox);
                handleInput(3, 3, context);
                break;
        }
    }

    public void resetGame() {
        for (int i = 0; i < fieldStatus.length; i++) {
            for (int y = 0; y < fieldStatus.length; y++) {
                fieldStatus[i][y] = 0;
            }
        }
    }
}
