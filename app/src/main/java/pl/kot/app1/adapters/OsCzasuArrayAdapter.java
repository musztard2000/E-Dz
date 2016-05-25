package pl.kot.app1.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spanned;
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
import pl.kot.app1.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.model.classes.Wydarzenie;
import pl.kot.app1.service.WydarzeniaService;

/**
 * Created by Damian on 21/05/2016.
 */
public class OsCzasuArrayAdapter extends ArrayAdapter<Wydarzenie> {
    private final int MAKSYMALNA_DLUGOSC_TEMATU_WIADOMOSCI_WYDARZENIA = 30;
    private final int MAKSYMALNA_DLUGOSC_TRESCI_WIAOMOSCI_WYDARZENIA = 50;
    private final String TRZY_KROPKI = "...";

    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN, new Locale("pl", "PL"));

    private final String TEMAT_WIADOMOSCI = "Temat:";
    private final String TRESC_WIADOMOSCI = "Treść:";
    private final String WARTOSC_OCENY = "Wartość:";
    private final String ZA_CO_OCENA = "Za co:";
    private final String HEADER_OCENY = "cena";
    private final String HEADER_WIADOMOSCI = "iadomość";

    private final Context context;
    private List<Wydarzenie> wydarzenia;
    private OdpowiedzNaLogowanie odpowiedzNaLogowanie;
    private TextView textViewInicjalWydarzenia;
    private TextView textViewHeaderWydarzenia;
    private TextView textViewDataWydarzenia;
    private TextView textViewNazwaWydarzenia;
    private TextView textViewNazwaWydarzeniaLabel;
    private TextView textViewZawartoscWydarzenia;
    private TextView textViewZawartoscWydarzeniaLabel;

    /**
     * ObiektView adaptera osi czasu jest niezbędny
     * by z innych klas wywoływać jego atrybut zmieniania
     * tła elementów ListView, przy onclicku.
     */
    private View osCzasuConverView;


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
            osCzasuConverView = convertView;
        }

        if (!przedmiotDataObject.isCzyPrzeczytane()) {
            convertView.setBackgroundColor(Color.parseColor("#b3b3ff"));
        } else {
            convertView.setBackgroundColor(-1);
        }

        inicjujKomponentyIPowiazZAtrybutamiModeluWydarzenia(przedmiotDataObject, convertView);

        return convertView;
    }

    private void inicjujKomponentyIPowiazZAtrybutamiModeluWydarzenia(Wydarzenie wydarzenie, View rowView) {
        inicjujKomponentyWidokou(rowView);

        powiazAtrybutyWidokuZModelemWydarzenia(wydarzenie);

        Log.i("WYDARZENIE: ",  wydarzenie.getNazwaWydarzenia());
    }

    private void inicjujKomponentyWidokou(View rowView) {
        textViewInicjalWydarzenia = (TextView) rowView.findViewById(R.id.typWydarzeniaTextView);
        textViewHeaderWydarzenia = (TextView) rowView.findViewById(R.id.headerText);

        textViewDataWydarzenia = (TextView) rowView.findViewById(R.id.dataWydarzeniaTextView);

        textViewNazwaWydarzenia = (TextView) rowView.findViewById(R.id.nazwaWydarzeniaText);
        textViewNazwaWydarzeniaLabel = (TextView) rowView.findViewById(R.id.nazwaWydarzeniaLabel);

        textViewZawartoscWydarzenia = (TextView) rowView.findViewById(R.id.zawartoscWydarzeniaText);
        textViewZawartoscWydarzeniaLabel = (TextView) rowView.findViewById(R.id.zawartoscWydarzeniaLabel);
    }

    private void powiazAtrybutyWidokuZModelemWydarzenia(Wydarzenie wydarzenie) {
        if(wydarzenie.getTypWydarzenia().startsWith("O")) {
            powiazAtrybutyWidokuZWydarzeniemTypuOcena(wydarzenie);
        } else {
            powiazAtrybutyWidokuZWydarzeniemTypuWiadomosc(wydarzenie);
        }

        powiazAtrybutDatyWidokuDlaWydarzenia(wydarzenie);
    }

    private void powiazAtrybutyWidokuZWydarzeniemTypuOcena(Wydarzenie wydarzenie) {
        textViewInicjalWydarzenia.setText("O");
        textViewHeaderWydarzenia.setText(HEADER_OCENY);
        textViewNazwaWydarzeniaLabel.setText(WARTOSC_OCENY);
        textViewZawartoscWydarzeniaLabel.setText(ZA_CO_OCENA);
        textViewNazwaWydarzenia.setText(wydarzenie.getNazwaWydarzenia());
        textViewNazwaWydarzenia.setTypeface(null, Typeface.BOLD);
        textViewZawartoscWydarzenia.setText(wydarzenie.getZawartoscWydarzenia());
    }

    private void powiazAtrybutyWidokuZWydarzeniemTypuWiadomosc(Wydarzenie wydarzenie) {
        textViewInicjalWydarzenia.setText("W");
        textViewHeaderWydarzenia.setText(HEADER_WIADOMOSCI);
        textViewNazwaWydarzeniaLabel.setText(TEMAT_WIADOMOSCI);
        textViewZawartoscWydarzeniaLabel.setText(TRESC_WIADOMOSCI);
        textViewNazwaWydarzenia.setTypeface(null, Typeface.NORMAL);

        skrocDlugoscTematuWiadomosciJesliTrzeba(wydarzenie);
        skrocDlugoscTresciWiadomosciJesliTrzeba(wydarzenie);
    }

    private void skrocDlugoscTematuWiadomosciJesliTrzeba(Wydarzenie wydarzenie) {
        if (wydarzenie.getNazwaWydarzenia().length() > MAKSYMALNA_DLUGOSC_TEMATU_WIADOMOSCI_WYDARZENIA) {
            String skroconaNazwaWydarzenia = wydarzenie.getNazwaWydarzenia().substring(0, 30);
            skroconaNazwaWydarzenia += TRZY_KROPKI;
            textViewNazwaWydarzenia.setText(skroconaNazwaWydarzenia);
        } else {
            textViewNazwaWydarzenia.setText(wydarzenie.getNazwaWydarzenia());
        }
    }

    private void skrocDlugoscTresciWiadomosciJesliTrzeba(Wydarzenie wydarzenie) {
        Spanned htmlowaTrescWiadomosci = Html.fromHtml(wydarzenie.getZawartoscWydarzenia());
        if (htmlowaTrescWiadomosci.length() > MAKSYMALNA_DLUGOSC_TRESCI_WIAOMOSCI_WYDARZENIA) {
            CharSequence charSequence = htmlowaTrescWiadomosci.subSequence(0, 50);
            String skroconaTrescWiadomosci = charSequence.toString();
            skroconaTrescWiadomosci += TRZY_KROPKI;
            textViewZawartoscWydarzenia.setText(skroconaTrescWiadomosci);
        } else {
            textViewZawartoscWydarzenia.setText(htmlowaTrescWiadomosci);
        }
    }

    private void powiazAtrybutDatyWidokuDlaWydarzenia(Wydarzenie wydarzenie) {
        if (wydarzenie.getDataWydarzenia() != null) {
            textViewDataWydarzenia.setText(SIMPLE_DATE_FORMAT.format(wydarzenie.getDataWydarzenia()));
        } else {
            textViewDataWydarzenia.setText("");
        }
    }

    public List<Wydarzenie> getWydarzenia() {
        return wydarzenia;
    }

    public void setWydarzenia(List<Wydarzenie> wydarzenia) {
        this.wydarzenia = wydarzenia;
    }
}