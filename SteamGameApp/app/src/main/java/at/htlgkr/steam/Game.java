package at.htlgkr.steam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Game {
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    private String name;
    private Date releaseDate;
    private double price;

    public Game(String name, Date releaseDate, double price) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.price = price;
    }
    public Game() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return "[" + simpleDateFormat.format(releaseDate) + "] " + name + " " + price;
    }

    public String toStringAlternative() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return name + ";" + simpleDateFormat.format(releaseDate) + ";" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

