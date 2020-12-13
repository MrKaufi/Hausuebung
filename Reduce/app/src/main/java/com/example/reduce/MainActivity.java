package com.example.reduce;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.reduce);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nominator = (EditText) findViewById(R.id.nominator);
                EditText denominator = (EditText) findViewById(R.id.denominator);
                TextView erg = (TextView) findViewById(R.id.ergebnis);

                erg.setText(reduce(Integer.parseInt(nominator.getText().toString()), Integer.parseInt(denominator.getText().toString()))+"");
            }

            public int reduce(int nominator, int denominator){
                if (nominator == 0)
                    return denominator;
                while (denominator != 0) {
                    if (nominator > denominator)
                        nominator = nominator - denominator;
                    else
                        denominator = denominator - nominator;
                }

                return nominator;
            }
        });
    }
}