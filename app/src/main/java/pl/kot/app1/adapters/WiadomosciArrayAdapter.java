package pl.kot.app1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import pl.kot.app1.R;
import pl.kot.app1.model.Wiadomosc;
import pl.kot.app1.service.WiadomosciService;

/**
 * Created by Damian on 20/04/2016.
 */
public class WiadomosciArrayAdapter extends ArrayAdapter<Wiadomosc> {
    private final Context context;
    private static List<Wiadomosc> listaWiadomosci;
    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN, new Locale("pl", "PL"));

    static {
        listaWiadomosci = new WiadomosciService().generujWiadmosci();
    }

    public WiadomosciArrayAdapter(Context context) {
        super(context, R.layout.wiadomosci_list_view, listaWiadomosci);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final Wiadomosc nazwaPrzedmiotu = listaWiadomosci.get(position);

        View rowView = inflater.inflate(R.layout.wiadomosci_list_view, parent, false);
        TextView textViewNadawca = (TextView) rowView.findViewById(R.id.textViewNadawca);
        TextView textViewTemat = (TextView) rowView.findViewById(R.id.oceny);
        TextView textViewData = (TextView) rowView.findViewById(R.id.textViewDataWiadomosci);
        textViewNadawca.setText(nazwaPrzedmiotu.getNadawca());
        textViewTemat.setText(nazwaPrzedmiotu.getTemat());
        textViewData.setText(SIMPLE_DATE_FORMAT.format(nazwaPrzedmiotu.getDataWyslania()));

        return rowView;
    }
}