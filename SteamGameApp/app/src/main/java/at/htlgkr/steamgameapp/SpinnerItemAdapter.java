package at.htlgkr.steamgameapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import at.htlgkr.steam.Game;

public class SpinnerItemAdapter extends ArrayAdapter<ReportTypeSpinnerItem> {
    List<ReportTypeSpinnerItem> spinnerItems;
    int spinnerLayoutId;
    LayoutInflater layoutInflater;

    public SpinnerItemAdapter(Context context, int resource, int textViewResourceID, List<ReportTypeSpinnerItem> spinnerItems) {
        super(context,resource);

        this.spinnerItems = spinnerItems;
        this.spinnerLayoutId = textViewResourceID;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return spinnerItems.size();
    }

    @Override
    public ReportTypeSpinnerItem getItem(int position) {
        return spinnerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View givenView, ViewGroup parent) {
        ReportTypeSpinnerItem spinnerItem = spinnerItems.get(position);
        View listItem = (givenView == null) ?layoutInflater.inflate(this.spinnerLayoutId, null) : givenView;
        ((TextView) listItem.findViewById(R.id.spinnerItemText)).setText(spinnerItem.getDisplayText());
        return listItem;
    }
}
