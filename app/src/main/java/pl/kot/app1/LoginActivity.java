package pl.kot.app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Damian on 19/04/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private Button btnZalogujSie;
    private EditText editTextLogin;
    private EditText editTextPass;
    private TextView textViewBlednyLoginLubHaslo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_layout);

        btnZalogujSie = (Button) findViewById(R.id.btnZalogujSie);
        editTextLogin = (EditText) findViewById(R.id.textEditLogin);
        editTextPass = (EditText) findViewById(R.id.editTextPassword);
        textViewBlednyLoginLubHaslo = (TextView) findViewById(R.id.textViewBlednyLoginLubHaaslo);
        btnZalogujSie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                zaloguj();
            }
        });

    }

    public void zaloguj() {
        final String login = editTextLogin.getText().toString();
        final String pass = editTextPass.getText().toString();

        if (zamokujUdaneLogowanie(login, pass)) {
            textViewBlednyLoginLubHaslo = null;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            textViewBlednyLoginLubHaslo.setText("Logowanie nieudane. Błędny login lub hasło.");
        }
    }

    private boolean zamokujUdaneLogowanie(String login, String password) {
        return ("dam".equals(login) && "kot".equals(password));
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
