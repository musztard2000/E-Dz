package pl.kot.app1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import pl.kot.app1.R;
import pl.kot.app1.model.classes.ZapisaneDaneAplikacji;
import pl.kot.app1.rest.RestProccessor;
import pl.kot.app1.rest.clients.ClientRestowyLogowanie;
import pl.kot.app1.service.LocalStorageProccessor;

/**
 * Created by Damian on 19/04/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private Button btnZalogujSie;
    private EditText editTextLogin;
    private EditText editTextPass;
    private TextView textViewBlednyLoginLubHaslo;
    private ZapisaneDaneAplikacji zapisaneDaneAplikacji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_layout);

        zapisaneDaneAplikacji = new LocalStorageProccessor(this).pobierzZapisaneDaneAplikacji();

        inicjujKomponenty();

        obsluzLogowanie();

    }

    private void obsluzLogowanie() {
        btnZalogujSie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click logowania.");
   //            zalogujMOCKOWO();

                zalogujPrzezREST();
            }
        });
    }

    private void inicjujKomponenty() {
        btnZalogujSie = (Button) findViewById(R.id.btnZalogujSie);
        editTextLogin = (EditText) findViewById(R.id.textEditLogin);
        editTextPass = (EditText) findViewById(R.id.editTextPassword);
        editTextPass.setTransformationMethod(new PasswordTransformationMethod());
        textViewBlednyLoginLubHaslo = (TextView) findViewById(R.id.textViewBlednyLoginLubHaaslo);
    }

    public void zalogujMOCKOWO() {
        final String login = editTextLogin.getText().toString();
        final String pass = editTextPass.getText().toString();

        if (zamokujUdaneLogowanie(login, pass)) {
            textViewBlednyLoginLubHaslo.setText(null);
            editTextLogin.setText(null);
            editTextPass.setText(null);
            Intent intent = new Intent(this, BusinessActivity.class);
            startActivity(intent);
        } else {
            textViewBlednyLoginLubHaslo.setText("Logowanie nieudane. Błędny login lub hasło.");
        }
    }

    private boolean zamokujUdaneLogowanie(String login, String password) {
        return ("dam".equals(login) && "kot".equals(password));
    }


    private void zalogujPrzezREST() {
        final String login = editTextLogin.getText().toString();
        final String pass = editTextPass.getText().toString();

        if(zapisaneDaneAplikacji != null) {
            System.out.println("ZAPISANE DANE ISTNIEJĄ, DATA OST. LOG: " + new Date(zapisaneDaneAplikacji.getDataOstatniegoLogowania()));
            new RestProccessor(new ClientRestowyLogowanie(this, login, pass, zapisaneDaneAplikacji)).execute();
        } else {
            System.out.println("NIE ISTNIEJĄ ZAPISANE DANE.");
            new RestProccessor(new ClientRestowyLogowanie(this, login, pass)).execute();
        }

    }

    public EditText getEditTextLogin() {
        return editTextLogin;
    }
    public EditText getEditTextPass() {
        return editTextPass;
    }

    public TextView getTextViewBlednyLoginLubHaslo() {
        return textViewBlednyLoginLubHaslo;
    }
}
