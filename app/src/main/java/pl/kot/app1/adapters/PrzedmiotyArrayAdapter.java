package pl.kot.app1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.kot.app1.R;
import pl.kot.app1.model.Przedmiot;
import pl.kot.app1.service.PrzedmiotyService;

/**
 * Created by Damian on 20/04/2016.
 */
public class PrzedmiotyArrayAdapter extends ArrayAdapter<Przedmiot> {
    private final Context context;
    private List<Przedmiot> przedmioty;

    public PrzedmiotyArrayAdapter(Context context) {
        super(context, R.layout.przedmioty_list_view);

        inicjujPrzedmioty();

        super.addAll(przedmioty);
        this.context = context;
    }

    private void inicjujPrzedmioty() {
        przedmioty = new PrzedmiotyService().generujPrzedmioty();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final Przedmiot przedmiotDataObject = przedmioty.get(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.przedmioty_list_view, parent, false);
        }

        inicjujKomponenty(przedmiotDataObject, convertView);

        return convertView;
    }

    private void inicjujKomponenty(Przedmiot przedmiot, View rowView) {
        TextView textViewNazwaPrzedmiotu = (TextView) rowView.findViewById(R.id.textViewNadawcaWiadomosci);
        TextView textViewLogoPrzedmiotu = (TextView) rowView.findViewById(R.id.logo);
        TextView textViewListaOcen = (TextView) rowView.findViewById(R.id.oceny);

        textViewNazwaPrzedmiotu.setText(przedmiot.getNazwaPrzedmiotu());
        textViewLogoPrzedmiotu.setText(String.valueOf(przedmiot.getNazwaPrzedmiotu().charAt(0)));
        textViewListaOcen.setText(przedmiot.getListaOcen());

        System.out.println(przedmiot.getNazwaPrzedmiotu());
    }
}