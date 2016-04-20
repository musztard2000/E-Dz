package pl.kot.app1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.kot.app1.model.Wiadomosc;
import pl.kot.app1.service.WiadomosciService;

/**
 * Created by Damian on 20/04/2016.
 */
public class WiadomosciArrayAdapter extends ArrayAdapter<Wiadomosc> {
    private final Context context;
    private static List<Wiadomosc> listaWiadomosci;

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
        TextView textViewNadawca = (TextView) rowView.findViewById(R.id.label);
        TextView textViewTemat = (TextView) rowView.findViewById(R.id.oceny);
        textViewNadawca.setText(nazwaPrzedmiotu.getNadawca());
        textViewTemat.setText(nazwaPrzedmiotu.getTemat());

        System.out.println(nazwaPrzedmiotu.getTresc());

        return rowView;
    }
}