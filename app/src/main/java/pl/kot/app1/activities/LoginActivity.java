package pl.kot.app1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pl.kot.app1.R;

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

        inicjujKomponenty();

        btnZalogujSie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                zaloguj();
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

    public void zaloguj() {
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

}
