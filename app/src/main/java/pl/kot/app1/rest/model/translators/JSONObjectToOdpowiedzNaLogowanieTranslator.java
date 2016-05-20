package pl.kot.app1.rest.model.translators;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pl.kot.app1.rest.model.classes.OcenaZPrzedmiotu;
import pl.kot.app1.rest.model.classes.OdpowiedzNaLogowanie;
import pl.kot.app1.rest.model.classes.Uzytkownik;
import pl.kot.app1.rest.model.classes.Wiadomosc;

/**
 * Created by Damian on 20/05/2016.
 */
public class JSONObjectToOdpowiedzNaLogowanieTranslator {


    private JSONObject odpowiedzJson;

    public JSONObjectToOdpowiedzNaLogowanieTranslator(JSONObject odpowiedzNaLogowanie) {
        this.odpowiedzJson = odpowiedzNaLogowanie;
    }


    public OdpowiedzNaLogowanie generuj() throws JSONException {

        OdpowiedzNaLogowanie odpowiedzNaLogowanie = new OdpowiedzNaLogowanie();

        odpowiedzNaLogowanie.setUzytkownik(translateJSONUzytkownikToUzytkownik());
        odpowiedzNaLogowanie.setOceny(translateJSONOcenyToOceny());
        odpowiedzNaLogowanie.setWiadomosci(translateJSONWiadomosciToWiadomosci());

        return odpowiedzNaLogowanie;
    }


    private Uzytkownik translateJSONUzytkownikToUzytkownik() throws JSONException {
        final JSONObject jsonUzytkownik = odpowiedzJson.getJSONObject("uzytkownik");

        Uzytkownik uzytkownik = new Uzytkownik();
        uzytkownik.setImie(jsonUzytkownik.getString("imie"));
        uzytkownik.setNazwisko(jsonUzytkownik.getString("nazwisko"));
        uzytkownik.setLogin(jsonUzytkownik.getString("login"));
        uzytkownik.setPassword(jsonUzytkownik.getString("password"));
        return uzytkownik;
    }

    private List<Wiadomosc> translateJSONWiadomosciToWiadomosci() throws JSONException {
        final JSONArray wiadomosciJSON = odpowiedzJson.getJSONArray("wiadomosci");
        List<Wiadomosc> wiadomosciTranslated = new ArrayList<>();

        for(int i = 0; i < wiadomosciJSON.length(); i++) {
            JSONObject wiadomoscJSON = wiadomosciJSON.getJSONObject(i);
            wiadomosciTranslated.add(translateSingleWiadomoscJSONToWiadomosc(wiadomoscJSON));
        }

        return wiadomosciTranslated;
    }

    private Wiadomosc translateSingleWiadomoscJSONToWiadomosc(JSONObject wiadomoscJSON) throws JSONException {
        Wiadomosc wiadomosc = new Wiadomosc();
        wiadomosc.setNadawca(wiadomoscJSON.getString("nadawca"));
        wiadomosc.setTemat(wiadomoscJSON.getString("temat"));
        wiadomosc.setTresc(wiadomoscJSON.getString("tresc"));

        String dateStr = wiadomoscJSON.getString("dataWydarzenia");
        wiadomosc.setDataWydarzenia(tlumaczStringDoDaty(dateStr));

        return wiadomosc;
    }


    private List<OcenaZPrzedmiotu> translateJSONOcenyToOceny() throws JSONException {
        final JSONArray wiadomosciJSON = odpowiedzJson.getJSONArray("oceny");
        List<OcenaZPrzedmiotu> wiadomosciTranslated = new ArrayList<>();

        for(int i = 0; i < wiadomosciJSON.length(); i++) {
            JSONObject ocenaJSON = wiadomosciJSON.getJSONObject(i);
            wiadomosciTranslated.add(translateSingleOcenaJSONToOcenaZPrzedmiotu(ocenaJSON));
        }

        return wiadomosciTranslated;
    }

    private OcenaZPrzedmiotu translateSingleOcenaJSONToOcenaZPrzedmiotu(JSONObject wiadomoscJSON) throws JSONException {
        OcenaZPrzedmiotu ocenaZPrzedmiotu = new OcenaZPrzedmiotu();
        ocenaZPrzedmiotu.setNazwaPrzedmiotu(wiadomoscJSON.getString("nazwaPrzedmiotu"));
        ocenaZPrzedmiotu.setWartoscOceny(wiadomoscJSON.getString("wartoscOceny"));
        ocenaZPrzedmiotu.setZaCoOcena(wiadomoscJSON.getString("zaCoOcena"));

        String dateStr = wiadomoscJSON.getString("dataWydarzenia");
        ocenaZPrzedmiotu.setDataWydarzenia(tlumaczStringDoDaty(dateStr));

        return ocenaZPrzedmiotu;
    }

    /**
     *
     * @param date - Reprezentacja napisowa daty w defaultowym formacie dla java.util.Date, czyli np: 2016-02-23T16:54:49.537
     * @return - Zwraca obiekt java.util.Date przetworzony odpowiednim patternem.
     */
    private Date tlumaczStringDoDaty(String date) {
        final String DEFULAT_JAVA_UTIL_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        final SimpleDateFormat sdf = new SimpleDateFormat(DEFULAT_JAVA_UTIL_DATE_PATTERN, Locale.ENGLISH);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}