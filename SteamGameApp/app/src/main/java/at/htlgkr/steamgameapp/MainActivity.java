package at.htlgkr.steamgameapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import at.htlgkr.steam.Game;
import at.htlgkr.steam.ReportType;
import at.htlgkr.steam.SteamBackend;

public class MainActivity extends AppCompatActivity {
    private static final String GAMES_CSV = "games.csv";
    ListView listView; //= new ListView(this);
    Spinner spinner; //= new Spinner(this);

    Button searchButton;
    Button saveButton;
    Button addGameButton;

    SteamBackend steamBackend = new SteamBackend();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.gamesList);
        spinner = findViewById(R.id.chooseReport);

        searchButton = findViewById(R.id.search);
        saveButton = findViewById(R.id.save);
        addGameButton = findViewById(R.id.addGame);

        loadGamesIntoListView();
        setUpReportSelection();
        setUpSearchButton();
        setUpAddGameButton();
        setUpSaveButton();
    }

    private void loadGamesIntoListView() {
        try(InputStream inputStream = getAssets().open(GAMES_CSV)){
            steamBackend.loadGames(inputStream);
            GameAdapter gameAdapter = new GameAdapter(this, R.layout.game_item_layout, steamBackend.getGames());
            listView.setAdapter(gameAdapter);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    private void setUpReportSelection() {
        ArrayList<ReportTypeSpinnerItem> reportTypeSpinnerItems = new ArrayList<>();
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.NONE, SteamGameAppConstants.SELECT_ONE_SPINNER_TEXT));
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.SUM_GAME_PRICES, SteamGameAppConstants.SUM_GAME_PRICES_SPINNER_TEXT));
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.AVERAGE_GAME_PRICES, SteamGameAppConstants.AVERAGE_GAME_PRICES_SPINNER_TEXT));
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.UNIQUE_GAMES, SteamGameAppConstants.UNIQUE_GAMES_SPINNER_TEXT));
        reportTypeSpinnerItems.add(new ReportTypeSpinnerItem(ReportType.MOST_EXPENSIVE_GAMES, SteamGameAppConstants.MOST_EXPENSIVE_GAMES_SPINNER_TEXT));

        SpinnerItemAdapter spinnerItemAdapter = new SpinnerItemAdapter(this, R.id.chooseReport ,R.layout.spinner_item_layout,  reportTypeSpinnerItems);
        spinnerItemAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerItemAdapter);


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setNeutralButton("ok", null);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        break;
                    case 1:
                        dialog.setMessage(SteamGameAppConstants.ALL_PRICES_SUM + steamBackend.sumGamePrices());
                        dialog.show();
                        break;
                    case 2:
                        dialog.setMessage(SteamGameAppConstants.ALL_PRICES_AVERAGE + steamBackend.averageGamePrice(  ));
                        dialog.show();
                        break;
                    case 3:
                        dialog.setMessage(SteamGameAppConstants.UNIQUE_GAMES_COUNT + steamBackend.getUniqueGames().size());
                        dialog.show();
                        break;
                    case 4:
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
                setUpReportSelection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpSearchButton() {
        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText searchField = new EditText(view.getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle(SteamGameAppConstants.ENTER_SEARCH_TERM);
                builder.setView(searchField);

                builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Game> newGames = new ArrayList<>();
                        if (!searchField.getText().equals(null)) {
                            for (int i = 0; i < steamBackend.getGames().size(); i++) {
                                if (steamBackend.getGames().get(i).getName().toLowerCase().contains(searchField.getText().toString().toLowerCase())) {
                                    newGames.add(steamBackend.getGames().get(i));
                                }
                            }
                            GameAdapter gameAdapter = new GameAdapter(view.getContext(), R.layout.game_item_layout, newGames);
                            listView.setAdapter(gameAdapter);
                        } else {
                            GameAdapter gameAdapter = new GameAdapter(view.getContext(), R.layout.game_item_layout, steamBackend.getGames());
                            listView.setAdapter(gameAdapter);
                        }
                        setUpSearchButton();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        setUpSearchButton();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void setUpAddGameButton() {
        addGameButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = new LinearLayout(view.getContext());

                final EditText gameName = new EditText(view.getContext());
                gameName.setId(R.id.dialog_name_field);
                gameName.setX(70);
                final EditText gameDate = new EditText(view.getContext());
                gameDate.setId(R.id.dialog_date_field);
                gameDate.setX(70);
                final EditText gamePrice = new EditText(view.getContext());
                gamePrice.setId(R.id.dialog_price_field);
                gamePrice.setX(70);

                linearLayout.addView(gameName);
                linearLayout.addView(gameDate);
                linearLayout.addView(gamePrice);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(linearLayout);
                builder.setTitle(SteamGameAppConstants.NEW_GAME_DIALOG_TITLE);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

                builder.setPositiveButton("Add Game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            steamBackend.addGame(new Game(gameName.getText().toString(), simpleDateFormat.parse(gameDate.getText().toString()), Double.parseDouble(gamePrice.getText().toString())));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                            loadGamesIntoListView();
                            setUpAddGameButton();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        setUpAddGameButton();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void setUpSaveButton(){
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                File gamesSaved = new File(SteamGameAppConstants.SAVE_GAMES_FILENAME);//eventuell muss auf assets gespeichert werden
                try {
                    steamBackend.store(new FileOutputStream(gamesSaved));
                    setUpSaveButton();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast toast = Toast.makeText(view.getContext(), "Games saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
