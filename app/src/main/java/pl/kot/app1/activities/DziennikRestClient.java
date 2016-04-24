package pl.kot.app1.activities;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * Klasa pośrednicząca w obsłudze GETÓW.
 * Skorzystałem z modelu DELEGATA. Przekazuję obiekt interfejsu,
 * i na metodach tego interfejsu operuję. Dzięki temu
 * w miejscach,w którep otrzebuję obliczenia róznego typu,
 * to w konstruktorze tej klasy wrzucam odpowiednio zaimplementowane
 * metody klas implementujących interfejs 'ClientRestowy'.
 *
 *
 * Created by Damian on 24/04/2016.
 */
public class DziennikRestClient extends AsyncTask<Void, Void, String> {

    /**
     * Obiekt typu clientRestowyImpl.
     */
    protected ClientRestowy clientRestowyImpl;

    public DziennikRestClient(ClientRestowy clientRestowy) {
        this.clientRestowyImpl = clientRestowy;
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(clientRestowyImpl.getRestResourceURL());
        String text = null;
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);
            HttpEntity entity = response.getEntity();

            text = getASCIIContentFromEntity(entity);

        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return text;
    }

    private String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
        InputStream in = entity.getContent();

        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n > 0) {
            byte[] b = new byte[4096];
            n = in.read(b);

            if (n > 0) out.append(new String(b, 0, n));
        }

        return out.toString();
    }

    /**
     * Metoda kończąca pracę wątku. Parametr results zawiera
     * wartość teksową zwróconą przez żądanie. GLOWNE ZADANIE:
     * ODSWIEZENIE KOMPONENTOW WIDOKU!
     *
     * @param results
     */
    @Override
    protected void onPostExecute(String results) {
        clientRestowyImpl.proccessView(results);
    }

}

