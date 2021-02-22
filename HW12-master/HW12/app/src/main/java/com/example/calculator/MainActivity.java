package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;

    Button buttonPlus;
    Button buttonMinus;
    Button buttonMul;
    Button buttonDiv;

    Button buttonOpen;
    Button buttonClose;

    Button buttonComma;
    Button buttonClear;
    Button buttonEquals;

    TextView textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);

        buttonOpen = findViewById(R.id.buttonOpen);
        buttonClose = findViewById(R.id.buttonClose);

        buttonComma = findViewById(R.id.buttonComma);
        buttonClear = findViewById(R.id.buttonClear);
        buttonEquals = findViewById(R.id.buttonEquals);

        textBox = findViewById(R.id.textBox);
    }

    public void numberClick(View view) {
        String text = (String) ((Button) view).getText();

        textBox.setText(String.format("%s%s", textBox.getText(), text));
    }

    public void clearClick(View view) {
        textBox.setText("");
    }

    public void solve(View view) {
        List<String> postFix = new PostFixConverter(textBox.getText()).getPostFixAsList();

        textBox.setText(String.format("%s", new PostFixCalculator(postFix).getResult().toString()));
        //textBox.setText(new PostFixConverter(textBox.getText()).getPostFixExpression());
    }
}