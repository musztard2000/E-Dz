package pl.kot.app1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;

/**
 * Created by Damian on 20/04/2016.
 */
public class BusinessActivity extends Activity{

    TabHost tabHost;


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

        //Tab 2
        TabHost.TabSpec spec = host.newTabSpec("Wiadomości");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Wiadomości");
        host.addTab(spec);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
