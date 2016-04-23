package pl.kot.app1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import pl.kot.app1.R;
import pl.kot.app1.model.Wiadomosc;

/**
 * Created by Damian on 22/04/2016.
 */
public class PodgladWiadomosciActivity extends Activity {

    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN, new Locale("pl", "PL"));
    private TextView textViewNadawca;
    private TextView textViewTemat;
    private TextView textViewData;
    private TextView textViewTresc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiadomosci_podglad);

        inicjujKomponenty();

        ustalKoloryKomponentow();

        final Wiadomosc OTWARTA_WIADOMOSC = (Wiadomosc) getIntent().getExtras().getSerializable("AKTUALNA_WIADOMOSC");
        ustalZawartoscKomponentowZOtwartejWiadomosci(OTWARTA_WIADOMOSC);

    }

    private void inicjujKomponenty() {
        textViewNadawca = (TextView) findViewById(R.id.wiadPogladNadawca);
        textViewTemat = (TextView) findViewById(R.id.wiadPodgladTemat);
        textViewData = (TextView) findViewById(R.id.wiadPodglData);
        textViewTresc = (TextView) findViewById(R.id.textViewTrescWiadomosci);
    }

    private void ustalZawartoscKomponentowZOtwartejWiadomosci(Wiadomosc otwartaWiadomosc) {
        textViewNadawca.setText(otwartaWiadomosc.getNadawca());
        textViewTemat.setText(otwartaWiadomosc.getTemat());
        textViewData.setText(SIMPLE_DATE_FORMAT.format(otwartaWiadomosc.getDataWyslania()));
        textViewTresc.setText(otwartaWiadomosc.getTresc());
    }

    private void ustalKoloryKomponentow() {
        textViewTresc.setMovementMethod(new ScrollingMovementMethod()); // tu ustalam scrollbar!
        textViewTresc.setBackgroundResource(R.color.white);
        textViewTresc.setTextColor(getResources().getColor(R.color.black));
    }

}