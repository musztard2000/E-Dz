package pl.kot.app1.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.kot.app1.R;
import pl.kot.app1.rest.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.rest.model.classes.Wydarzenie;
import pl.kot.app1.service.WydarzeniaService;

/**
 * Created by Damian on 21/05/2016.
 */
public class OsCzasuArrayAdapter extends ArrayAdapter<Wydarzenie> {
    private final Context context;
    private List<Wydarzenie> wydarzenia;
    private OdpowiedzNaLogowanie odpowiedzNaLogowanie;

    public OsCzasuArrayAdapter(Context context, OdpowiedzNaLogowanie odpowiedzNaLogowanie) {
        super(context, R.layout.timeline_list_view);
        this.odpowiedzNaLogowanie = odpowiedzNaLogowanie;
        inicjujWydarzenia();

        super.addAll(wydarzenia);
        this.context = context;
    }
    private void inicjujWydarzenia() {
        wydarzenia = new WydarzeniaService().generujWydarzenia(odpowiedzNaLogowanie);
        System.out.println("wydarzenia: " + wydarzenia);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final Wydarzenie przedmiotDataObject = wydarzenia.get(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.timeline_list_view, parent, false);
        }

        inicjujKomponenty(przedmiotDataObject, convertView);

        return convertView;
    }

    private void inicjujKomponenty(Wydarzenie wydarzenie, View rowView) {
        TextView textViewNazwaWydarzenia = (TextView) rowView.findViewById(R.id.nazwaWydarzeniaTextView);
        TextView textViewOpisWydarzenia = (TextView) rowView.findViewById(R.id.opisWydarzenia);
        TextView textViewTypWydarzenia = (TextView) rowView.findViewById(R.id.typWydarzeniaTextView);

        textViewNazwaWydarzenia.setText(wydarzenie.getNazwaWydarzenia());
        textViewOpisWydarzenia.setText(String.valueOf(wydarzenie.getZawartoscWydarzenia()));
        textViewTypWydarzenia.setText(String.valueOf(wydarzenie.getTypWydarzenia().charAt(0)));

        Log.i("WYDARZENIE: ",  wydarzenie.getNazwaWydarzenia());
    }

}
