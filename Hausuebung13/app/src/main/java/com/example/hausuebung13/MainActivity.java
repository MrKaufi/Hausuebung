package com.example.hausuebung13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView zuege;
    TextView spieler;

    Button feld11;
    Button feld12;
    Button feld13;
    Button feld21;
    Button feld22;
    Button feld23;
    Button feld31;
    Button feld32;
    Button feld33;

    Button[] felder = {feld11, feld12, feld13, feld21, feld22, feld23, feld31, feld32, feld33};
    Logik logik = new Logik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zuege = findViewById(R.id.zuege);
        zuege.setText("9");
        spieler = findViewById(R.id.spieler);
        spieler.setText("Spieler: O");

        felder[0] = findViewById(R.id.feld_11);
        felder[1] = findViewById(R.id.feld_12);
        felder[2] = findViewById(R.id.feld_13);
        felder[3] = findViewById(R.id.feld_21);
        felder[4] = findViewById(R.id.feld_22);
        felder[5] = findViewById(R.id.feld_23);
        felder[6] = findViewById(R.id.feld_31);
        felder[7] = findViewById(R.id.feld_32);
        felder[8] = findViewById(R.id.feld_33);

        felder[0].setOnClickListener(this);
        felder[1].setOnClickListener(this);
        felder[2].setOnClickListener(this);
        felder[3].setOnClickListener(this);
        felder[4].setOnClickListener(this);
        felder[5].setOnClickListener(this);
        felder[6].setOnClickListener(this);
        felder[7].setOnClickListener(this);
        felder[8].setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        logik.onClick(view, felder, getApplicationContext());
        zuege.setText(Integer.toString(logik.zuege));
        spieler.setText("Spieler: " + logik.ox);
        if (logik.checkFinished() || logik.checkDraw()) {
            zuege = findViewById(R.id.zuege);
            zuege.setText("9");
            spieler = findViewById(R.id.spieler);
            spieler.setText("Spieler: O");

            felder[0].setText("");
            felder[1].setText("");
            felder[2].setText("");
            felder[3].setText("");
            felder[4].setText("");
            felder[5].setText("");
            felder[6].setText("");
            felder[7].setText("");
            felder[8].setText("");
            logik.resetGame();
        }
    }
}



