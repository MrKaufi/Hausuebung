package at.htlgkr.steamgameapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import at.htlgkr.steam.Game;

public class GameAdapter extends BaseAdapter {

    List<Game> games;
    int listViewItemLayoutId;
    LayoutInflater layoutInflater;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public GameAdapter(Context context, int listViewItemLayoutId, List<Game> games) {
        this.games = games;
        this.listViewItemLayoutId = listViewItemLayoutId;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Object getItem(int position) {
        return games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View givenView, ViewGroup parent) {
        Game game = games.get(position);
        View listItem = (givenView == null) ?layoutInflater.inflate(this.listViewItemLayoutId, null) : givenView;
        ((TextView) listItem.findViewById(R.id.gameName)).setText(game.getName());
        ((TextView) listItem.findViewById(R.id.releaseDate)).setText(simpleDateFormat.format(game.getReleaseDate()).toString());
        ((TextView) listItem.findViewById(R.id.price)).setText(String.valueOf(game.getPrice()));
        return listItem;
    }
}
