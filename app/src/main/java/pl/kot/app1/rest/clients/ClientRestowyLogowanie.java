package pl.kot.app1.rest.clients;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import pl.kot.app1.activities.BusinessActivity;
import pl.kot.app1.activities.LoginActivity;
import pl.kot.app1.rest.ClientRestowy;
import pl.kot.app1.rest.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.rest.model.translators.JSONObjectToOdpowiedzNaLogowanieTranslator;

/**
 * Created by Damian on 24/04/2016.
 */
public class ClientRestowyLogowanie implements ClientRestowy {

    /**
     * Wszystkie klasy typu Activity dziedziczą z klasy Context.
     * Dlatego tutaj umieszam referencję do 'Activity', które korzysta z RestProccessor.
     */
    public Context context;
    private String login;
    private String password;

    /**
     * Data w milisekundach w formacie String, którą przekazuje jako jeden z parametrów
     * w query stringu. Jest to data ostatniego logowania (w przypadku pierwszego logowania
     * będzie wynosiła 0), będzie ona konieczna przy wykonywaniu przez aplikację na serwerze
     * zapytań o wydarzenia, które wydarzyły się pomiędzy ostatnim logowaniem, a datą aktualną.
     */
    private String dataOstatniegoLogowaniaJakoLong;

    public ClientRestowyLogowanie(Context context, String login, String password) {
        this.context = context;
        this.login = login;
        this.password = password;

        ustalDateOstatniegoLogowania();
    }

    private void ustalDateOstatniegoLogowania() {
        final long przykladowaData = 0l;
        dataOstatniegoLogowaniaJakoLong = Long.toString(new Date(przykladowaData).getTime());
    }

    @Override
    public String getRestResourceURL() {
        return URL_LOGOWANIE + "/?login=" + login + "&haslo=" + password + "&dataOstLog=" + dataOstatniegoLogowaniaJakoLong;
    }

    @Override
    public void proccessView(String inputText) {
        if (inputText != null && !inputText.isEmpty()) {

            Intent intent = new Intent(context, BusinessActivity.class);

            zacznijObslugeJSONowejTresciIPrzekazDoNowegoActivity(inputText, intent);
            czyscStroneLogowaniaIOtworzBusinessActivity(intent);
        } else {
            ((LoginActivity) context).getTextViewBlednyLoginLubHaslo().setText("Logowanie nieudane. Błędny login lub hasło.");
        }
    }

    private void zacznijObslugeJSONowejTresciIPrzekazDoNowegoActivity(String inputText, Intent intent) {
        System.out.println("INPUT TEXT OF CLIENT RESTOWY LOGOWANIE: " + inputText);
        try {
            JSONObject odpowiedzJSON = new JSONObject(inputText);

            zalogujElementyOdpowiedzi(odpowiedzJSON);

            OdpowiedzNaLogowanie odpowiedzNaLogowanie = new JSONObjectToOdpowiedzNaLogowanieTranslator(odpowiedzJSON).generuj();
            System.out.println(odpowiedzNaLogowanie);
            intent.putExtra("ODPOWIEDZ_NA_LOGOWANIE", odpowiedzNaLogowanie);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void zalogujElementyOdpowiedzi(JSONObject odpowiedzNaLogowanie) throws JSONException {
        JSONObject uzytkownik = odpowiedzNaLogowanie.getJSONObject("uzytkownik");
        Log.i("INFO", "UZYTKOWNIK: " + uzytkownik);

        JSONArray wiadomosci = odpowiedzNaLogowanie.getJSONArray("wiadomosci");
        Log.i("INFO", "WIADOMOSCI: " + wiadomosci);

        JSONArray ocenyZPrzedmiotow = odpowiedzNaLogowanie.getJSONArray("oceny");
        Log.i("INFO", "OCENY: " + ocenyZPrzedmiotow);
    }

    private void czyscStroneLogowaniaIOtworzBusinessActivity(Intent intent) {
        ((LoginActivity) context).getTextViewBlednyLoginLubHaslo().setText(null);
        ((LoginActivity) context).getEditTextLogin().setText(null);
        ((LoginActivity) context).getEditTextPass().setText(null);
        context.startActivity(intent);
    }
}
