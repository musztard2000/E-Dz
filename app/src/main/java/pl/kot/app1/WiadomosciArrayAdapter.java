package pl.kot.app1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Damian on 20/04/2016.
 */
public class WiadomosciArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public WiadomosciArrayAdapter(Context context, String[] values) {
        super(context, R.layout.wiadomosci_list_view, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final String nazwaPrzedmiotu = values[position];

        View rowView = inflater.inflate(R.layout.wiadomosci_list_view, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        TextView ocenyView = (TextView) rowView.findViewById(R.id.oceny);
        textView.setText(nazwaPrzedmiotu);
        ocenyView.setText("Temat: Przykładowy temat.");
        System.out.println(nazwaPrzedmiotu);

        return rowView;
    }
}