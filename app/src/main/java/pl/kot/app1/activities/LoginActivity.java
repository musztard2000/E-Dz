package pl.kot.app1.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pl.kot.app1.R;
import pl.kot.app1.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.model.classes.ZapisaneDaneAplikacji;
import pl.kot.app1.model.classes.ZapisaneDaneUzytkownika;
import pl.kot.app1.rest.RestProccessor;
import pl.kot.app1.rest.clients.ClientRestowyLogowanie;
import pl.kot.app1.service.LocalStorageProccessor;
import pl.kot.app1.service.OdpowiedzNaLogowanieService;

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

    @Override
    protected void onResume() {
        super.onResume();

        zapisaneDaneAplikacji = new LocalStorageProccessor(this).pobierzZapisaneDaneAplikacji();
    }

    private void obsluzLogowanie() {
        btnZalogujSie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click logowania.");
   //            zalogujMOCKOWO();
                if (isNetworkAvailable()) {
                    zalogujPrzezREST();
                } else {
                    zalogujOffline();
                    Log.e("Brak sieci", "NIe udało się połączyc.");
                }
            }
        });
    }

    private void zalogujOffline() {
        final String wprowadzonyLogin = editTextLogin.getText().toString();
        final String wprowadzoneHaslo = editTextPass.getText().toString();

        if (zapisaneDaneAplikacji == null) {
            textViewBlednyLoginLubHaslo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewBlednyLoginLubHaslo.setText("Pierwsze logowanie wymaga połączenia internetowego.");
        } else {
            boolean czyTrzebaDodacNowegoUzytkownika = true;

            for (ZapisaneDaneUzytkownika zapisaneDaneUzytkownika : zapisaneDaneAplikacji.getZapisaneDaneUzytkownikaList()) {
                if (zapisaneDaneUzytkownika.getUzytkownik().getLogin().equals(wprowadzonyLogin)
                        && zapisaneDaneUzytkownika.getUzytkownik().getPassword().equals(wprowadzoneHaslo)) {

                    OdpowiedzNaLogowanie odpowiedzNaLogowanieOffline = new OdpowiedzNaLogowanieService(zapisaneDaneUzytkownika).generujOdpowiedzOffline();
                    otworzBusinessActivityOffline(odpowiedzNaLogowanieOffline);

                    czyTrzebaDodacNowegoUzytkownika = false;
                }
            }

            if (czyTrzebaDodacNowegoUzytkownika) {
                textViewBlednyLoginLubHaslo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textViewBlednyLoginLubHaslo.setText("Brak połączenia internetowego. Błędny login lub hasło.");
            }
        }

    }

    private void otworzBusinessActivityOffline(OdpowiedzNaLogowanie odpowiedzNaLogowanieOffline) {
        Intent intent = new Intent(this, BusinessActivity.class);

        intent.putExtra("ODPOWIEDZ_NA_LOGOWANIE", odpowiedzNaLogowanieOffline);
        intent.putExtra("ZAPISANE_DANE_APLIKACJI", zapisaneDaneAplikacji);

        getTextViewBlednyLoginLubHaslo().setText(null);
        getEditTextLogin().setText(null);
        getEditTextPass().setText(null);
        startActivity(intent);
    }

    private void inicjujKomponenty() {
        btnZalogujSie = (Button) findViewById(R.id.btnZalogujSie);
        editTextLogin = (EditText) findViewById(R.id.textEditLogin);
        editTextPass = (EditText) findViewById(R.id.editTextPassword);
        editTextPass.setTransformationMethod(new PasswordTransformationMethod());
        textViewBlednyLoginLubHaslo = (TextView) findViewById(R.id.textViewBlednyLoginLubHaaslo);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void zalogujPrzezREST() {
        final String wprowadzonyLogin = editTextLogin.getText().toString();
        final String wprowadzoneHaslo = editTextPass.getText().toString();


        if (zapisaneDaneAplikacji == null) {
            zalogujIUtworzLocalStorage(wprowadzonyLogin, wprowadzoneHaslo);
        } else {
            zalogujZIstniejacymPlikiemLocalStorage(wprowadzonyLogin, wprowadzoneHaslo);
        }
    }

    private void zalogujIUtworzLocalStorage(String wprowadzonyLogin, String wprowadzoneHaslo) {
        System.out.println("NIE ISTNIEJĄ ZAPISANE DANE.");

        new RestProccessor(new ClientRestowyLogowanie(this, wprowadzonyLogin, wprowadzoneHaslo)).execute();
    }

    private void zalogujZIstniejacymPlikiemLocalStorage(String wprowadzonyLogin, String wprowadzoneHaslo) {
        boolean czyTrzebaDodacNowegoUzytkownika = true;

        for (ZapisaneDaneUzytkownika zapisaneDaneUzytkownika : zapisaneDaneAplikacji.getZapisaneDaneUzytkownikaList()) {
            if (zapisaneDaneUzytkownika.getUzytkownik().getLogin().equals(wprowadzonyLogin)
                    && zapisaneDaneUzytkownika.getUzytkownik().getPassword().equals(wprowadzoneHaslo)) {

                new RestProccessor(new ClientRestowyLogowanie(this, wprowadzonyLogin, wprowadzoneHaslo, zapisaneDaneAplikacji, zapisaneDaneUzytkownika)).execute();
                czyTrzebaDodacNowegoUzytkownika = false;
            }
        }

        if (czyTrzebaDodacNowegoUzytkownika) {
            System.out.println("dodaje nowego uzytkownika: " + wprowadzonyLogin);
            new RestProccessor(new ClientRestowyLogowanie(this, wprowadzonyLogin, wprowadzoneHaslo, zapisaneDaneAplikacji)).execute();
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
