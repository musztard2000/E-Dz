package pl.kot.app1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;

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

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        przygotujTabOcen(host);
        przygotujTabWiadomosci(host);

    }

    private void przygotujTabOcen(TabHost host) {
        ListView listView = (ListView) findViewById(R.id.listViewPrzedmioty);
        listView.setAdapter(new PrzedmiotyArrayAdapter(this));

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Oceny");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Oceny");
        host.addTab(spec);
    }

    private void przygotujTabWiadomosci(TabHost host) {
        ListView wiadomosciListView = (ListView) findViewById(R.id.listViewWiadomosci);
        wiadomosciListView.setAdapter(new WiadomosciArrayAdapter(this));
        wiadomosciListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Wiadomosc aktualnaWiadomsc = (Wiadomosc) adapterView.getItemAtPosition(i);
                przejdzDoPodgladu(aktualnaWiadomsc);
            }
        });

        //Tab 2
        TabHost.TabSpec spec = host.newTabSpec("Wiadomości");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Wiadomości");
        host.addTab(spec);

    }

    private void przejdzDoPodgladu(Wiadomosc aktualnieWybranaWiadomosc) {
        Intent intent = new Intent(this, PodgladWiadomosciActivity.class);
        intent.putExtra("AKTUALNA_WIADOMOSC", aktualnieWybranaWiadomosc);
        startActivity(intent);

    }
}
