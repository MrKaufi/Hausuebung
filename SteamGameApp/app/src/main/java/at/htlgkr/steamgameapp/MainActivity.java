package at.htlgkr.steamgameapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
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
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 0, R.id.chooseReport, reportTypeSpinnerItems);

        spinner.setAdapter(arrayAdapter);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setNeutralButton("ok", null);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        dialog.setMessage(SteamGameAppConstants.ALL_PRICES_SUM + steamBackend.sumGamePrices());
                        dialog.show();
                        break;
                    case 1:
                        dialog.setMessage(SteamGameAppConstants.ALL_PRICES_AVERAGE + steamBackend.averageGamePrice());
                        dialog.show();
                        break;
                    case 2:
                        dialog.setMessage(SteamGameAppConstants.UNIQUE_GAMES_COUNT + steamBackend.getUniqueGames().size());
                        dialog.show();
                    case 3:
                        String message = "";
                        List topGames = steamBackend.selectTopNGamesDependingOnPrice(3);
                        for (int j = 0; j < 3; j++) {
                            message += topGames.get(j).toString();
                            if (j != 2) message += "\n";
                        }
                        dialog.setMessage(SteamGameAppConstants.MOST_EXPENSIVE_GAMES + message);
                        dialog.show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpSearchButton() {
        View vDialog = getLayoutInflater().inflate(R.layout.search_dialog, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(SteamGameAppConstants.ENTER_SEARCH_TERM);
        EditText searchField = vDialog.findViewById(R.id.searchField);

        dialog.setPositiveButton("search", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Game> newGames = new ArrayList<>();
                if (!searchField.getText().equals(null)) {
                    for (int i = 0; i < steamBackend.getGames().size(); i++) {
                        if (steamBackend.getGames().get(i).getName().toLowerCase().contains(searchField.getText().toString().toLowerCase())) {
                            newGames.add(steamBackend.getGames().get(i));
                        }
                    }
                    GameAdapter gameAdapter = new GameAdapter(this, R.id.gamesList, newGames);
                    listView.setAdapter(gameAdapter);
                } else {
                    GameAdapter gameAdapter = new GameAdapter(this, R.id.gamesList, steamBackend.getGames());
                    listView.setAdapter(gameAdapter);
                }
            }
        });

        dialog.setNegativeButton("cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
        });

    }

    private void setUpAddGameButton() {
        AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.new_game_dialog, null);
        EditText gameName = dialogView.findViewById(R.id.dialog_name_field);
        EditText gameDate = dialogView.findViewById(R.id.dialog_date_field);
        EditText gamePrice = dialogView.findViewById(R.id.dialog_price_field);

        dialogBuilder.
    }

    private void setUpSaveButton() {
        // Implementieren Sie diese Methode.
    }
}
