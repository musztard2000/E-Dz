package pl.kot.app1.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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

    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN, new Locale("pl", "PL"));


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

        inicjujKomponentyIPowiazZAtrybutamiModeluWydarzenia(przedmiotDataObject, convertView);

        return convertView;
    }

    private void inicjujKomponentyIPowiazZAtrybutamiModeluWydarzenia(Wydarzenie wydarzenie, View rowView) {
        TextView textViewInicjalWydarzenia = (TextView) rowView.findViewById(R.id.typWydarzeniaTextView);
        TextView textViewHeaderWydarzenia = (TextView) rowView.findViewById(R.id.headerText);

        TextView textViewDataWydarzenia = (TextView) rowView.findViewById(R.id.dataWydarzeniaTextView);

        TextView textViewNazwaWydarzenia = (TextView) rowView.findViewById(R.id.nazwaWydarzeniaText);
        TextView textViewNazwaWydarzeniaLabel = (TextView) rowView.findViewById(R.id.nazwaWydarzeniaLabel);

        TextView textViewZawartoscWydarzenia = (TextView) rowView.findViewById(R.id.zawartoscWydarzeniaText);
        TextView textViewZawartoscWydarzeniaLabel = (TextView) rowView.findViewById(R.id.zawartoscWydarzeniaLabel);

        if(wydarzenie.getTypWydarzenia().startsWith("O")) {
            textViewInicjalWydarzenia.setText("O");
            textViewHeaderWydarzenia.setText("cena");
            textViewNazwaWydarzeniaLabel.setText("Wartość:");
            textViewZawartoscWydarzeniaLabel.setText("Za co:");
            textViewNazwaWydarzenia.setTypeface(null, Typeface.BOLD);
        } else {
            textViewInicjalWydarzenia.setText("W");
            textViewHeaderWydarzenia.setText("iadomość");
            textViewNazwaWydarzeniaLabel.setText("Temat:");
            textViewZawartoscWydarzeniaLabel.setText("Treść:");
            textViewNazwaWydarzenia.setTypeface(null, Typeface.NORMAL);
        }

        textViewNazwaWydarzenia.setText(wydarzenie.getNazwaWydarzenia());
        textViewZawartoscWydarzenia.setText(wydarzenie.getZawartoscWydarzenia());

        if (wydarzenie.getDataWydarzenia() != null) {
            textViewDataWydarzenia.setText(SIMPLE_DATE_FORMAT.format(wydarzenie.getDataWydarzenia()));
        } else {
            textViewDataWydarzenia.setText("");
        }
        Log.i("WYDARZENIE: ",  wydarzenie.getNazwaWydarzenia());
    }

}