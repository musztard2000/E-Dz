package pl.kot.app1.rest.clients;

import android.content.Context;
import android.content.Intent;

import pl.kot.app1.activities.BusinessActivity;
import pl.kot.app1.activities.LoginActivity;
import pl.kot.app1.rest.ClientRestowy;

/**
 * Created by Damian on 24/04/2016.
 */
public class ClientRestowyLogowanie implements ClientRestowy {
    /**
     * Referencja do 'Activity', które akurat wywoła RestProccessor, które wykona
     * pracę RESTOWĄ, a które tutaj będzie miało manipulowane jakoś atrybuty
     * swoich komponentów.
     */
    public Context context;
    private String login;
    private String password;

    public ClientRestowyLogowanie(Context context, String login, String password) {
        this.context = context;
        this.login = login;
        this.password = password;
    }

    @Override
    public String getRestResourceURL() {
        return URL_OPIEKUN + "/?login=" + login + "&haslo=" + password;
    }

    @Override
    public void proccessView(String inputText) {
        if (inputText != null && !inputText.isEmpty()) {
            System.out.println("INPUT TEXT OF CLIENT RESTOWY LOGOWANIE: " + inputText);
            ((LoginActivity) context).getTextViewBlednyLoginLubHaslo().setText(null);
            ((LoginActivity) context).getEditTextLogin().setText(null);
            ((LoginActivity) context).getEditTextPass().setText(null);
            Intent intent = new Intent(context, BusinessActivity.class);
            context.startActivity(intent);
        } else {
            ((LoginActivity) context).getTextViewBlednyLoginLubHaslo().setText("Logowanie nieudane. Błędny login lub hasło.");
        }
    }
}
