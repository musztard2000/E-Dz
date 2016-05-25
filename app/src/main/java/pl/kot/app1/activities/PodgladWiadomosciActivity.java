package pl.kot.app1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import pl.kot.app1.R;
import pl.kot.app1.model.classes.Wiadomosc;

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
    private Wiadomosc otwartaWiadomosc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiadomosci_podglad);

        inicjujKomponenty();

        ustalKoloryKomponentow();

        /*
         * Jest to obiekt, który jest odebrany wprost z miejsca, które inicjuje tę klasę.
         * Na ten moment, jest to kliknięcie wiadomości z listy wiadomości.
         *
         */
        otwartaWiadomosc = (Wiadomosc) getIntent().getExtras().getSerializable("AKTUALNA_WIADOMOSC");
        ustalZawartoscKomponentowZOtwartejWiadomosci(otwartaWiadomosc);
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
        textViewData.setText(SIMPLE_DATE_FORMAT.format(otwartaWiadomosc.getDataWydarzenia()));

        /*
        poniżej korzystam ze statycznej metody .fromHtml( ...trescHtml...) klasy HTML
        która jest na możliwości TextView konwertowania jak się da.
         */
        textViewTresc.setText(Html.fromHtml(otwartaWiadomosc.getTresc()));
    }

    private void ustalKoloryKomponentow() {
        textViewTresc.setMovementMethod(new ScrollingMovementMethod()); // tu ustalam scrollbar!
        textViewTresc.setBackgroundResource(R.color.white);
        textViewTresc.setTextColor(getResources().getColor(R.color.black));
    }

    public TextView getTextViewTresc() {
        return textViewTresc;
    }
}


