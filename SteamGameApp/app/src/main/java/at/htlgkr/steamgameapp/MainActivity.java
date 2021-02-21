package at.htlgkr.steamgameapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.htlgkr.steam.Game;
import at.htlgkr.steam.ReportType;
import at.htlgkr.steam.SteamBackend;

public class MainActivity extends AppCompatActivity {
    private static final String GAMES_CSV = "games.csv";

    SteamBackend steamBackend = new SteamBackend();
    ListView listView = findViewById(R.id.gamesList);
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            loadGamesIntoListView();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setUpReportSelection();
        setUpSearchButton();
        setUpAddGameButton();
        setUpSaveButton();
    }

    private void loadGamesIntoListView() throws IOException, ParseException {
        File file = new File(GAMES_CSV);
        InputStream inputStream = new FileInputStream(file);
        steamBackend.loadGames(inputStream);
        GameAdapter gameAdapter = new GameAdapter(this, R.id.gamesList, steamBackend.getGames());

        listView.setAdapter(gameAdapter);
    }

    private void setUpReportSelection() {
        ArrayList<ReportTypeSpinnerItem> reportTypeSpinnerItems = new ArrayList<>();
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.NONE, SteamGameAppConstants.SELECT_ONE_SPINNER_TEXT));
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.SUM_GAME_PRICES, SteamGameAppConstants.SUM_GAME_PRICES_SPINNER_TEXT));
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.AVERAGE_GAME_PRICES, SteamGameAppConstants.AVERAGE_GAME_PRICES_SPINNER_TEXT));
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.UNIQUE_GAMES, SteamGameAppConstants.UNIQUE_GAMES_SPINNER_TEXT));
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.MOST_EXPENSIVE_GAMES, SteamGameAppConstants.MOST_EXPENSIVE_GAMES_SPINNER_TEXT));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,0, R.id.chooseReport, reportTypeSpinnerItems);

        spinner.setAdapter(arrayAdapter);

        AlertDialog.Builder dialoge = new AlertDialog.Builder(this);
        dialoge.setNeutralButton("ok", null);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        dialoge.setMessage(SteamGameAppConstants.ALL_PRICES_SUM + steamBackend.sumGamePrices());
                        dialoge.show();
                        break;
                    case 1:
                        dialoge.setMessage(SteamGameAppConstants.ALL_PRICES_AVERAGE + steamBackend.averageGamePrice());
                        dialoge.show();
                        break;
                    case 2:
                        dialoge.setMessage(SteamGameAppConstants.UNIQUE_GAMES_COUNT + steamBackend.getUniqueGames().size());
                        dialoge.show();
                    case 3:
                        String message = "";
                        List topGames = steamBackend.selectTopNGamesDependingOnPrice(3);
                        for (int j = 0; j < 3; j++) {
                            message += topGames.get(j).toString() ;
                            if (j != 2) message += "\n";
                        }
                        dialoge.setMessage(SteamGameAppConstants.MOST_EXPENSIVE_GAMES + message);
                        dialoge.show();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpSearchButton() {
        View vDialog = getLayoutInflater().inflate(R.layout.search_field, null);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(SteamGameAppConstants.ENTER_SEARCH_TERM);


    }

    private void setUpAddGameButton() {
        // Implementieren Sie diese Methode.
    }

    private void setUpSaveButton() {
        // Implementieren Sie diese Methode.
    }
}
