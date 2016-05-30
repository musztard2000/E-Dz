package pl.kot.app1.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import pl.kot.app1.R;
import pl.kot.app1.adapters.OsCzasuArrayAdapter;
import pl.kot.app1.adapters.PrzedmiotyArrayAdapter;
import pl.kot.app1.adapters.WiadomosciArrayAdapter;
import pl.kot.app1.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.model.classes.Wiadomosc;
import pl.kot.app1.model.classes.Wydarzenie;
import pl.kot.app1.model.classes.ZapisaneDaneAplikacji;
import pl.kot.app1.model.classes.ZapisaneDaneUzytkownika;
import pl.kot.app1.service.LocalStorageProccessor;

/**
 * Jest to activity które uruchamia się po udanym logowaniu.
 * W jego metodzie <code>onCreate</code> odbywa się m. in.
 * ładowanie treści po zalogowaniu.
 *
 * Created by Damian on 20/04/2016.
 */
public class BusinessActivity extends Activity{

    private OsCzasuArrayAdapter osCzasuArrayAdapter;
    private OdpowiedzNaLogowanie odpowiedzNaLogowanie;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("INSIDE ON CREATE.");
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_layout);

        final TabHost tabHostOcenIWiadomosci = (TabHost)findViewById(R.id.tabHost);
        tabHostOcenIWiadomosci.setup();

        /*
        ten obiekt to rezultat udanego zalogowania.
        Zawiera wszystkie dane do działania aplikacji.
         */
        odpowiedzNaLogowanie = (OdpowiedzNaLogowanie) getIntent().getExtras().getSerializable("ODPOWIEDZ_NA_LOGOWANIE");

        dodajListenerNawigacjiPomiedzyTabami(tabHostOcenIWiadomosci);

        przygotujTabOsiCzasu(tabHostOcenIWiadomosci);
        przygotujTabOcen(tabHostOcenIWiadomosci);
        przygotujTabWiadomosci(tabHostOcenIWiadomosci);
    }

    /**
     * Poniższy kod służy do obsługi zmiany tabów. Zmienia rozmiar i kolor tabom aktywnym i nieaktywnym.
     * @param host
     */
    private void dodajListenerNawigacjiPomiedzyTabami(final TabHost host) {
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                //Unselected Tabs
                for (int i = 0; i < host.getTabWidget().getChildCount(); i++) {
                    TextView tv = (TextView) host.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setTextSize(12);
                }

                //for Selected Tab
                TextView tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title);
                tv.setTextColor(Color.parseColor("#ffffff"));
                tv.setTextSize(16);
            }
        });
    }

    private void przygotujTabOsiCzasu(TabHost host) {
        TabHost.TabSpec tabOsiCzasu = host.newTabSpec("Oś czasu");
        tabOsiCzasu.setContent(R.id.tabOsiCzasu);
        tabOsiCzasu.setIndicator("Oś czasu");
        host.addTab(tabOsiCzasu);

        wypelnijListViewOsiCzasu();
    }

    private void wypelnijListViewOsiCzasu() {
        ListView osCzasuListView = (ListView) findViewById(R.id.listViewTimeline);

        osCzasuArrayAdapter = new OsCzasuArrayAdapter(this, odpowiedzNaLogowanie);
        osCzasuListView.setAdapter(osCzasuArrayAdapter);

        dodajListenerOdznaczaniaNieprzeczytanychWydarzen(osCzasuListView);
    }

    private void dodajListenerOdznaczaniaNieprzeczytanychWydarzen(ListView wydarzeniaListView) {
        wydarzeniaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Wydarzenie aktualneWydazrenie = (Wydarzenie) adapterView.getItemAtPosition(i);
                aktualneWydazrenie.setCzyPrzeczytane(true);
                System.out.println("wydarzenie przeczytane o nazwie: " + aktualneWydazrenie.getNazwaWydarzenia());
                view.setBackgroundColor(-1);
            }
        });
    }

    private void przygotujTabOcen(TabHost host) {
        TabHost.TabSpec tabOcen = host.newTabSpec("Oceny");
        tabOcen.setContent(R.id.tabOcen);
        tabOcen.setIndicator("Oceny");
        host.addTab(tabOcen);

        wypelnijListViewOceny();
    }

    private void wypelnijListViewOceny() {
        ListView przedmiotyListView = (ListView) findViewById(R.id.listViewPrzedmioty);
        przedmiotyListView.setAdapter(new PrzedmiotyArrayAdapter(this, odpowiedzNaLogowanie));
    }

    private void przygotujTabWiadomosci(TabHost host) {
        TabHost.TabSpec tabWiadomosci = host.newTabSpec("Wiadomości");
        tabWiadomosci.setContent(R.id.tabWiadomosci);
        tabWiadomosci.setIndicator("Wiadomości");
        host.addTab(tabWiadomosci);

        wypelnijListViewWiadomosci();
    }

    private void wypelnijListViewWiadomosci() {
        ListView wiadomosciListView = (ListView) findViewById(R.id.listViewWiadomosci);

        wiadomosciListView.setAdapter(new WiadomosciArrayAdapter(this, odpowiedzNaLogowanie));

        dodajListenerPodgladuAktualnieWybranejWiadomosci(wiadomosciListView);
    }

    private void dodajListenerPodgladuAktualnieWybranejWiadomosci(ListView wiadomosciListView) {
        wiadomosciListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Wiadomosc aktualnaWiadomsc = (Wiadomosc) adapterView.getItemAtPosition(i);
                przejdzDoPodgladuWybranejWiadomosci(aktualnaWiadomsc);
            }
        });
    }

    /**
     * Otwiera nowe activity aktualnie wybranej wiadomosci.
     * W tej meodzie przekazywany jest obiekt 'Wiadomosc' pomiedzy
     * dwoma activity.
     *
     * @param aktualnieWybranaWiadomosc - Aktualnie wybrana wiadomosc, ktora
     *                                  jest przekazywana pomiedzy dwoma activity.
     */
    private void przejdzDoPodgladuWybranejWiadomosci(Wiadomosc aktualnieWybranaWiadomosc) {
        Intent intent = new Intent(this, PodgladWiadomosciActivity.class);
        intent.putExtra("AKTUALNA_WIADOMOSC", aktualnieWybranaWiadomosc);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        new LocalStorageProccessor(this).zapiszDoLocalStoragePlik(utworzZapisaneDaneAplikacji());
    }

    private ZapisaneDaneAplikacji utworzZapisaneDaneAplikacji() {
        utworzDaneAktualnegoUzytkownikaDoZapisania();

        ZapisaneDaneAplikacji zapisaneDaneAplikacji = (ZapisaneDaneAplikacji) getIntent().getExtras().getSerializable("ZAPISANE_DANE_APLIKACJI");

        if (zapisaneDaneAplikacji == null) {
            return utworzNowyPlikZapisaneDaneAplikacji();
        } else {
            return zwrocZaktualizowaneZapisaneDaneAplikacji();
        }
    }

    private ZapisaneDaneAplikacji zwrocZaktualizowaneZapisaneDaneAplikacji() {
        ZapisaneDaneAplikacji zapisaneIstniejaceDaneAplikacji = (ZapisaneDaneAplikacji) getIntent().getExtras().getSerializable("ZAPISANE_DANE_APLIKACJI");

        final ZapisaneDaneUzytkownika daneUzytkownikaDoZapisaniaLubAktualizacji = utworzDaneAktualnegoUzytkownikaDoZapisania();

        return dodajLubAktualizujZapisaneDaneAplikacji(zapisaneIstniejaceDaneAplikacji, daneUzytkownikaDoZapisaniaLubAktualizacji);
    }

    private ZapisaneDaneAplikacji dodajLubAktualizujZapisaneDaneAplikacji(ZapisaneDaneAplikacji zapisaneIstniejaceDaneAplikacji, ZapisaneDaneUzytkownika daneUzytkownikaDoZapisaniaLubAktualizacji) {

        int indeksUzytkownikaDoZamienienia = -1;
        for (ZapisaneDaneUzytkownika uzytkownik : zapisaneIstniejaceDaneAplikacji.getZapisaneDaneUzytkownikaList()) {
            if (uzytkownik.getUzytkownik().getLogin().equals(daneUzytkownikaDoZapisaniaLubAktualizacji.getUzytkownik().getLogin())) {
                indeksUzytkownikaDoZamienienia = zapisaneIstniejaceDaneAplikacji.getZapisaneDaneUzytkownikaList().indexOf(uzytkownik);
            }
        }

        if (indeksUzytkownikaDoZamienienia != -1) {
            Log.e("ZAPISANE_DANE", "AKTUALIZUJE  uzytkownika: " + daneUzytkownikaDoZapisaniaLubAktualizacji.getUzytkownik().getLogin());
            zapisaneIstniejaceDaneAplikacji.getZapisaneDaneUzytkownikaList().set(indeksUzytkownikaDoZamienienia, daneUzytkownikaDoZapisaniaLubAktualizacji);
        } else {
            Log.e("ZAPISANE_DANE", "DODAJE UZYTKOWNIKA uzytkownika: " + daneUzytkownikaDoZapisaniaLubAktualizacji.getUzytkownik().getLogin());
            zapisaneIstniejaceDaneAplikacji.getZapisaneDaneUzytkownikaList().add(daneUzytkownikaDoZapisaniaLubAktualizacji);
        }

        return zapisaneIstniejaceDaneAplikacji;
    }

    @NonNull
    private ZapisaneDaneAplikacji utworzNowyPlikZapisaneDaneAplikacji() {
        final ZapisaneDaneUzytkownika daneUzytkownikaDoZapisania = utworzDaneAktualnegoUzytkownikaDoZapisania();

        ZapisaneDaneAplikacji zapisaneDaneAplikacji;
        zapisaneDaneAplikacji = new ZapisaneDaneAplikacji();
        zapisaneDaneAplikacji.setZapisaneDaneUzytkownikaList(new ArrayList<ZapisaneDaneUzytkownika>());
        zapisaneDaneAplikacji.getZapisaneDaneUzytkownikaList().add(daneUzytkownikaDoZapisania);
        Log.e("ZAPISANE_DANE", "Utworzylem nowe zapisane dane aplikacji z uzytkownikiem: " + daneUzytkownikaDoZapisania.getUzytkownik().getLogin());
        return zapisaneDaneAplikacji;
    }

    private ZapisaneDaneUzytkownika utworzDaneAktualnegoUzytkownikaDoZapisania() {
        ZapisaneDaneUzytkownika zapisaneDaneUzytkownika = new ZapisaneDaneUzytkownika();
        zapisaneDaneUzytkownika.setDataOstatniegoLogowania(new Date().getTime());
        zapisaneDaneUzytkownika.setUzytkownik(odpowiedzNaLogowanie.getUzytkownik());
        zapisaneDaneUzytkownika.setWydarzenia(osCzasuArrayAdapter.getWydarzenia());
        return zapisaneDaneUzytkownika;
    }
}