package com.example.hausuebung12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hausuebung12.arithmeticutils.PostFixConverter;
import com.example.hausuebung12.arithmeticutils.PostfixCalculator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonNull;
    Button buttonEins;
    Button buttonZwei;
    Button buttonDrei;
    Button buttonVier;
    Button buttonFuenf;
    Button buttonSechs;
    Button buttonSieben;
    Button buttonAcht;
    Button buttonNeun;
    Button buttonAddieren;
    Button buttonSubtrahieren;
    Button buttonMultiplizieren;
    Button buttonAuf;
    Button buttonZu;
    Button buttonKommer;
    Button buttonClear;
    Button buttonGleich;
    TextView rechenfeld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rechenfeld = findViewById(R.id.rechenFeld);

        buttonNull = findViewById(R.id.buttonNull);
        buttonEins = findViewById(R.id.buttonEins);
        buttonZwei = findViewById(R.id.buttonZwei);
        buttonDrei = findViewById(R.id.buttonDrei);
        buttonVier = findViewById(R.id.buttonVier);
        buttonFuenf = findViewById(R.id.buttonFuenf);
        buttonSechs = findViewById(R.id.buttonSechs);
        buttonSieben = findViewById(R.id.buttonSieben);
        buttonAcht = findViewById(R.id.buttonAcht);
        buttonNeun = findViewById(R.id.buttonNeun);
        buttonAddieren = findViewById(R.id.buttonAddieren);
        buttonSubtrahieren = findViewById(R.id.buttonSubtrahieren);
        buttonMultiplizieren = findViewById(R.id.buttonMultiplizieren);
        buttonAuf = findViewById(R.id.buttonAuf);
        buttonZu = findViewById(R.id.buttonZu);
        buttonKommer = findViewById(R.id.buttonKommer);
        buttonClear = findViewById(R.id.buttonClear);
        buttonGleich = findViewById(R.id.buttonGleich);
    }

    @Override
    public void onClick(View view) {
        String text = (String) ((Button) view).getText();

        rechenfeld.setText(String.format("%s%s", rechenfeld.getText(), text));
    }


    public void clear(View view) {
        rechenfeld.setText("");
    }

    public void gleich(View view) {
        Log.e("", "a"+ rechenfeld.getText().toString());

        //rechenfeld.setText(new PostFixConverter(rechenfeld.getText().toString()).getInfixExpression());

        PostFixConverter postFixConverter = new PostFixConverter((String) rechenfeld.getText());
        PostfixCalculator postfixCalculator = new PostfixCalculator(postFixConverter.getPostfixAsList());
        rechenfeld.setText(postfixCalculator.getResult().toString());
    }
}