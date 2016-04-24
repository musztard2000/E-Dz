package pl.kot.app1.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import pl.kot.app1.R;
import pl.kot.app1.adapters.PrzedmiotyArrayAdapter;
import pl.kot.app1.adapters.WiadomosciArrayAdapter;
import pl.kot.app1.model.Wiadomosc;

/**
 * Created by Damian on 20/04/2016.
 */
public class BusinessActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_layout);

        final TabHost tabHostOcenIWiadomosci = (TabHost)findViewById(R.id.tabHost);
        tabHostOcenIWiadomosci.setup();

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
        przedmiotyListView.setAdapter(new PrzedmiotyArrayAdapter(this));
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
        wiadomosciListView.setAdapter(new WiadomosciArrayAdapter(this));
        wiadomosciListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Wiadomosc aktualnaWiadomsc = (Wiadomosc) adapterView.getItemAtPosition(i);
                przejdzDoPodgladuWybranejWiadomosci(aktualnaWiadomsc);
            }
        });
    }

    private void przejdzDoPodgladuWybranejWiadomosci(Wiadomosc aktualnieWybranaWiadomosc) {
        Intent intent = new Intent(this, PodgladWiadomosciActivity.class);
        intent.putExtra("AKTUALNA_WIADOMOSC", aktualnieWybranaWiadomosc);
        startActivity(intent);
    }

}
