package at.htlgkr.steam;


import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SteamBackend {

    private List<Game> games;

    public SteamBackend() {
        this.games = new ArrayList<>();
    }


    public void loadGames(InputStream inputStream) throws IOException, ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        bufferedReader.readLine();
        String line;
        while ((line = bufferedReader.readLine() ) != null){
            String[] splitGame = line.split(";");
            Date date = simpleDateFormat.parse(splitGame[1]);
            Game game = new Game(splitGame[0], date, Double.valueOf(splitGame[2]));
            addGame(game);
        }

    }

    public void store(OutputStream fileOutputStream) throws IOException {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(fileOutputStream));
        for (int i = 0; i < games.size(); i++) {
            printWriter.println(games.get(i).toStringAlternative());
        }
        printWriter.flush();
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game newGame) {
        games.add(newGame);
    }

    public double sumGamePrices() {
        double sum = 0;
        for (int i = 0; i < games.size(); i++) {
            sum += games.get(i).getPrice();
        }
        return sum;
    }

    public double averageGamePrice() {
        double sum = 0;
        for (int i = 0; i < games.size(); i++) {
            sum += games.get(i).getPrice();
        }
        return sum/games.size();
    }

    public List<Game> getUniqueGames() {
        List<Game> uniqueGames = new ArrayList<>();
        for (int i = 0; i < games.size(); i++) {
            if (!uniqueGames.contains(games.get(i))){
                uniqueGames.add(games.get(i));
            }
        }
        return uniqueGames;
    }

    public List<Game> selectTopNGamesDependingOnPrice(int n) {
        List<Game> topGames = new ArrayList<>();
        List<Game> tempGames = games;
        Game tempGame = new Game("game", new Date(1,1,1), Double.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < tempGames.size(); j++) {
                if (tempGames.get(j).getPrice() > tempGame.getPrice()){
                    tempGame = tempGames.get(j);
                }
            }
            tempGames.remove(tempGame);
            topGames.add(tempGame);
            tempGame = new Game("game", new Date(1,1,1), Double.MIN_VALUE);
        }
        return topGames;
    }
}

