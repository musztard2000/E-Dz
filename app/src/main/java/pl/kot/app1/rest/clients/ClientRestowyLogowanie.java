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
import pl.kot.app1.model.classes.ZapisaneDaneAplikacji;
import pl.kot.app1.model.classes.ZapisaneDaneUzytkownika;
import pl.kot.app1.rest.ClientRestowy;
import pl.kot.app1.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.model.translators.JSONObjectToOdpowiedzNaLogowanieTranslator;
import pl.kot.app1.service.OdpowiedzNaLogowanieService;

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
    ZapisaneDaneUzytkownika zapisaneDaneUzytkownika;
    /**
     * Data w milisekundach w formacie String, którą przekazuje jako jeden z parametrów
     * w query stringu. Jest to data ostatniego logowania (w przypadku pierwszego logowania
     * będzie wynosiła 0), będzie ona konieczna przy wykonywaniu przez aplikację na serwerze
     * zapytań o wydarzenia, które wydarzyły się pomiędzy ostatnim logowaniem, a datą aktualną.
     */
    private String dataOstatniegoLogowaniaJakoLong;

    /**
     * Plik z zapisanymi danymi aplikacji.
     */
    private ZapisaneDaneAplikacji zapisaneDaneAplikacji;

    /**
     * Konstruktor wykorzystywany w przypadku gdy nie istnieje plik z local storage.
     * @param context - kontekst
     * @param login - login
     * @param password - hasło
     */
    public ClientRestowyLogowanie(Context context, String login, String password) {
        this.context = context;
        this.login = login;
        this.password = password;

        ustalDateOstatniegoLogowania(0l);
    }

    /**
     * Ten konstruktor klienta wywołuje sie, gdy w local storage aplikacji
     * znajduje się użytkownik zgodny z parametrami logowania aplikacji.
     * @param context - kontekst
     * @param login - login
     * @param pass - hasło
     * @param zapisaneDaneUzytkownika - zapisane dane użytkownika,
     *                                znajdujące się już w local storage aplikacji.
     */
    public ClientRestowyLogowanie(Context context, String login, String pass, ZapisaneDaneAplikacji zapisaneDaneAplikacji, ZapisaneDaneUzytkownika zapisaneDaneUzytkownika) {
        this.context = context;
        this.login = login;
        this.password = pass;
        this.zapisaneDaneUzytkownika = zapisaneDaneUzytkownika;
        this.zapisaneDaneAplikacji = zapisaneDaneAplikacji;

        ustalDateOstatniegoLogowania(zapisaneDaneUzytkownika.getDataOstatniegoLogowania());
    }

    /**
     *Ten konstruktor klienta wywołuje się w przypadku gdy istnieje już plik local storage
     * aplikacji, ale w tym pliku nie znajduje się użytkownik o podanych parametrach logowania.
     * @param context - kontekst
     * @param login - login
     * @param pass - hasło
     * @param zapisaneDaneAplikacji - zapisane dane aplikacji.
     */
    public ClientRestowyLogowanie(LoginActivity context, String login, String pass, ZapisaneDaneAplikacji zapisaneDaneAplikacji) {
        this.context = context;
        this.login = login;
        this.password = pass;
        this.zapisaneDaneAplikacji = zapisaneDaneAplikacji;

        ustalDateOstatniegoLogowania(0l);
    }

    private void ustalDateOstatniegoLogowania(long przykladowaData) {
        dataOstatniegoLogowaniaJakoLong = Long.toString(new Date(przykladowaData).getTime());
    }

    @Override
    public String getRestResourceURL() {
        return URL_LOGOWANIE + "/?login=" + login + "&haslo=" + password + "&dataOstLog=" + dataOstatniegoLogowaniaJakoLong;
    }

    @Override
    public void proccessView(String jsonResult) {
        if (jsonResult != null && !jsonResult.isEmpty()) {

            Intent intent = new Intent(context, BusinessActivity.class);

            zacznijObslugeJSONowejTresciIPrzekazDoNowegoActivity(jsonResult, intent);
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
            odpowiedzNaLogowanie = new OdpowiedzNaLogowanieService(odpowiedzNaLogowanie, zapisaneDaneUzytkownika).generuj();
            intent.putExtra("ODPOWIEDZ_NA_LOGOWANIE", odpowiedzNaLogowanie);

            zalogujCzyIstniejaDaneAplikacjiWLocalStorage();
            intent.putExtra("ZAPISANE_DANE_APLIKACJI", zapisaneDaneAplikacji);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void zalogujCzyIstniejaDaneAplikacjiWLocalStorage() {
        if (zapisaneDaneAplikacji == null) {
            Log.e("CLIENT RESTOWY- dane", "Zapisane dane to null");
        } else {
            Log.e("CLIENT RESTOWY- dane", "Zapisane dane to <NIE> null");
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
